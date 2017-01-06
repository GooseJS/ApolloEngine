package com.goosejs.apollo.audio;

import com.goosejs.apollo.util.IOUtils;
import com.goosejs.apollo.util.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.stb.STBVorbisInfo;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.openal.ALC11.*;
import static org.lwjgl.openal.EXTThreadLocalContext.*;
import static org.lwjgl.stb.STBVorbis.*;
import static org.lwjgl.stb.STBVorbisInfo.*;

public class AudioMaster
{

    public static void init()
    {
        long device = alcOpenDevice((ByteBuffer)null);

        ALCCapabilities deviceCaps = ALC.createCapabilities(device);

        long context = alcCreateContext(device, (IntBuffer)null);
        alcSetThreadContext(context);
        AL.createCapabilities(deviceCaps);

        playFile("sundaySchool.ogg");
    }

    private static void playFile(String file)
    {
        int buffer = alGenBuffers();
        int source = alGenSources();

        try ( STBVorbisInfo info = STBVorbisInfo.malloc() ) {
            ShortBuffer pcm = readVorbis(file, 32 * 1024, info);

            //copy to buffer
            alBufferData(buffer, info.channels() == 1 ? AL_FORMAT_MONO16 : AL_FORMAT_STEREO16, pcm, info.sample_rate());
        }

        alSourcei(source, AL_BUFFER, buffer);
        alSourcei(source, AL_LOOPING, AL_TRUE);
        alSourcePlay(source);
    }

    private static ShortBuffer readVorbis(String resource, int bufferSize, STBVorbisInfo info) {
        ByteBuffer vorbis;
        try {
            vorbis = IOUtils.fileToByteBufferE(resource, bufferSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        IntBuffer error = BufferUtils.createIntBuffer(1);
        long decoder = stb_vorbis_open_memory(vorbis, error, null);
        int errorCode = error.get(0);
        if ( decoder == MemoryUtil.NULL )
            throw new RuntimeException("Failed to open Ogg Vorbis file. Error: " + errorCode);

        stb_vorbis_get_info(decoder, info);

        int channels = info.channels();

        int lengthSamples = stb_vorbis_stream_length_in_samples(decoder);

        ShortBuffer pcm = BufferUtils.createShortBuffer(lengthSamples);

        pcm.limit(stb_vorbis_get_samples_short_interleaved(decoder, channels, pcm) * channels);
        stb_vorbis_close(decoder);

        return pcm;
    }

}

package com.goosejs.apollo.util;

import org.lwjgl.BufferUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * TODO: Documentation
 */
public class IOUtils
{

    private IOUtils() {}

    public static ByteBuffer fileToByteBuffer(String file, int bufferSize)
    {
        try
        {
            return fileToByteBufferE(file, bufferSize);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static ByteBuffer fileToByteBufferE(String file, int bufferSize) throws IOException
    {
        ByteBuffer buffer;

        Path path = Paths.get(file);
        if (Files.isReadable(path))
        {
            SeekableByteChannel fc = Files.newByteChannel(path);
            buffer = BufferUtils.createByteBuffer((int)fc.size() + 1);
            while (fc.read(buffer) != -1);
        }
        else
        {
            InputStream source = IOUtils.class.getClassLoader().getResourceAsStream(file);
            ReadableByteChannel rbc = Channels.newChannel(source);
            buffer = BufferUtils.createByteBuffer(bufferSize);
            while (true)
            {
                int bytes = rbc.read(buffer);
                if (bytes == -1)
                    break;
                if (buffer.remaining() == 0)
                    buffer = ApolloBufferUtils.resizeBuffer(buffer, buffer.capacity() * 2);
            }
        }

        buffer.flip();
        return buffer;
    }

    public static String getStringFromFile(File file)
    {
        try
        {
            return getStringFromFile(new Scanner(file), false);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace(); // TODO: Handle this better
        }
        return "";
    }

    public static String getStringFromFile(String file)
    {
        return getStringFromFile(new Scanner(IOUtils.class.getClassLoader().getResourceAsStream(file)), false);
    }

    public static String getStringFromFileWithoutComments(File file)
    {
        try
        {
            return getStringFromFile(new Scanner(file), true);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace(); // TODO: Handle this better
        }
        return "";
    }

    public static String getStringFromFileWithoutComments(String file)
    {
        return getStringFromFile(new Scanner(IOUtils.class.getClassLoader().getResourceAsStream(file)), true);
    }

    public static String getStringFromFile(Scanner scanner, boolean commentsIgnored)
    {
        StringBuilder stringBuilder = new StringBuilder();

        while (scanner.hasNext())
        {
            String line = scanner.nextLine();
            if (commentsIgnored)
            {
                if (!line.startsWith("//"))
                    stringBuilder.append(line).append("\n");
            }
            else
                stringBuilder.append(line).append("\n");
        }

        scanner.close();

        return stringBuilder.toString();
    }
}
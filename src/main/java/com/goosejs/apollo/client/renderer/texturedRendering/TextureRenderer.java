package com.goosejs.apollo.client.renderer.texturedRendering;

import java.util.ArrayList;
import java.util.HashMap;

public class TextureRenderer
{

    //private HashMap<Integer, HashMap<Integer, ArrayList<ITexturedRenders>>> renders; TODO: Look into batch rendering support

    public TextureRenderer()
    {

    }

    public interface ITexturedRenders
    {
        void onTexturedRender();
    }

}
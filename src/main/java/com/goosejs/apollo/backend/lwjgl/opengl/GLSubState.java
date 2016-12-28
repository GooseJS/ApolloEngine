package com.goosejs.apollo.backend.lwjgl.opengl;

import static org.lwjgl.opengl.GL11.*;

public interface GLSubState
{

    void resetState();

    abstract class BooleanState implements GLSubState
    {
        public static boolean enabledGlobal;

        private boolean enabled;

        public BooleanState(boolean enabled)
        {
            this.enabled = enabled;
        }

        public void setEnabled(boolean enabled)
        {
            if (enabledGlobal != enabled)
            {
                enabledGlobal = enabled;
                this.enabled = enabled;
                stateReset();
            }
        }

        public boolean isEnabled()
        {
            return enabled;
        }

        public void resetState()
        {
            setEnabled(enabled);
        }

        protected abstract void stateReset();
    }

    class CullState implements GLSubState
    {
        private static boolean enabledGlobal;
        private static int globalCullType;

        private boolean enabled;
        private int cullType;

        public CullState(boolean enabled, int cullType)
        {
            this.enabled = enabled;
            this.cullType = cullType;
        }

        public void setStateSettings(boolean enabled, int cullType)
        {
            setEnabled(enabled);
            setCullType(cullType);
        }

        public void setEnabled(boolean enabled)
        {
            if (enabledGlobal != enabled)
            {
                enabledGlobal = enabled;
                this.enabled = enabled;
                if (enabled)
                    glEnable(GL_CULL_FACE);
                else
                    glDisable(GL_CULL_FACE);
            }
        }

        public void setCullType(int cullType)
        {
            if (globalCullType != cullType)
            {
                globalCullType = cullType;
                this.cullType = cullType;
                glCullFace(cullType);
            }
        }

        public boolean isEnabled()
        {
            return enabled;
        }

        public int getCullType()
        {
            return cullType;
        }

        @Override
        public void resetState()
        {
            setEnabled(enabled);
            glCullFace(cullType);
        }
    }

    class BlendState implements GLSubState
    {
        private static boolean enabledGlobal;
        private static int globalSFactor;
        private static int globalDFactor;

        private boolean enabled;
        private int sFactor;
        private int dFactor;

        public BlendState(boolean enabled, int sFactor, int dFactor)
        {
            this.enabled = enabled;
            this.sFactor = sFactor;
            this.dFactor = dFactor;
        }

        public void setStateSettings(boolean enabled, int sFactor, int dFactor)
        {
            setEnabled(enabled);
            setBlendFunc(sFactor, dFactor);
        }

        public void setEnabled(boolean enabled)
        {
            if (enabledGlobal != enabled)
            {
                enabledGlobal = enabled;
                this.enabled = enabled;
                if (enabled)
                    glEnable(GL_BLEND);
                else
                    glDisable(GL_BLEND);
            }
        }

        public void setBlendFunc(int sFactor, int dFactor)
        {
            if (globalSFactor != sFactor || globalDFactor != dFactor)
            {
                globalSFactor = sFactor;
                globalDFactor = dFactor;
                this.sFactor = sFactor;
                this.dFactor = dFactor;
                glBlendFunc(sFactor, dFactor);
            }
        }

        public boolean isEnabled()
        {
            return enabled;
        }

        public int getSFactor()
        {
            return sFactor;
        }

        public int getDFactor()
        {
            return dFactor;
        }

        @Override
        public void resetState()
        {
            setEnabled(enabled);
            setBlendFunc(sFactor, dFactor);
        }
    }

    class LightingState extends BooleanState
    {
        public LightingState(boolean enabled)
        {
            super(enabled);
        }

        @Override
        public void stateReset()
        {
            if (isEnabled())
                glEnable(GL_LIGHTING);
            else
                glDisable(GL_LIGHTING);
        }
    }

    class DepthState extends BooleanState
    {
        public DepthState(boolean enabled)
        {
            super(enabled);
        }

        @Override
        public void stateReset()
        {
            if (isEnabled())
                glEnable(GL_DEPTH_TEST);
            else
                glDisable(GL_DEPTH_TEST);
        }
    }

    class ColorState implements GLSubState
    {
        private static float globalR;
        private static float globalG;
        private static float globalB;
        private static float globalA;

        private float r;
        private float g;
        private float b;
        private float a;

        public ColorState(float r, float g, float b, float a)
        {
            this.r = r;
            this.g = g;
            this.b = b;
            this.a = a;
        }

        public void setColor(float r, float g, float b, float a)
        {
            if (globalR != r || globalG != g || globalB != b || globalA != a)
            {
                globalR = r;
                globalG = g;
                globalB = b;
                globalA = a;
                this.r = r;
                this.g = g;
                this.b = b;
                this.a = a;
                glClearColor(r, g, b, a);
            }
        }

        public float getR()
        {
            return r;
        }

        public float getG()
        {
            return g;
        }

        public float getB()
        {
            return b;
        }

        public float getA()
        {
            return a;
        }

        @Override
        public void resetState()
        {
            setColor(r, g, b, a);
        }
    }

}
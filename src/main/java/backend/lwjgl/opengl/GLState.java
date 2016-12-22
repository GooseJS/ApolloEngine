package backend.lwjgl.opengl;

import org.lwjgl.opengl.GL11;

public class GLState
{

    private GLSubState.CullState cullState;
    private GLSubState.BlendState blendState;
    private GLSubState.DepthState depthState;
    private GLSubState.LightingState lightingState;
    private GLSubState.ColorState colorState;

    public GLState(GLState copyState)
    {
        cullState = new GLSubState.CullState(copyState.isCullingEnabled(), copyState.getCullType());
        blendState = new GLSubState.BlendState(copyState.isBlendingEnabled(), copyState.getBlendingSFactor(), copyState.getBlendingDFactor());
        depthState = new GLSubState.DepthState(copyState.isDepthEnabled());
        lightingState = new GLSubState.LightingState(copyState.isLightingEnabled());
        colorState = new GLSubState.ColorState(copyState.getR(), copyState.getG(), copyState.getB(), copyState.getA());
    }

    public GLState()
    {
        cullState = new GLSubState.CullState(false, GL11.GL_BACK);
        blendState = new GLSubState.BlendState(false, GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        depthState = new GLSubState.DepthState(false);
        lightingState = new GLSubState.LightingState(false);
        colorState = new GLSubState.ColorState(1, 1, 1, 1);
    }

    //region Culling

    public void enableCulling()
    {
        if (!cullState.isEnabled())
            cullState.setEnabled(true);
    }

    public void setCullType(int cullType)
    {
        if (cullState.getCullType() != cullType)
            cullState.setCullType(cullType);
    }

    public void disableCulling()
    {
        if (cullState.isEnabled())
            cullState.setEnabled(false);
    }

    public boolean isCullingEnabled()
    {
        return cullState.isEnabled();
    }

    public int getCullType()
    {
        return cullState.getCullType();
    }

    //endregion

    //region Blending

    public void enableBlending()
    {
        if (!blendState.isEnabled())
            blendState.setEnabled(true);
    }

    public void setBlendFunc(int sFactor, int dFactor)
    {
        if (blendState.getSFactor() != sFactor || blendState.getDFactor() != dFactor)
            blendState.setBlendFunc(sFactor, dFactor);
    }

    public void disableBlending()
    {
        if (blendState.isEnabled())
            blendState.setEnabled(false);
    }

    public boolean isBlendingEnabled()
    {
        return blendState.isEnabled();
    }

    public int getBlendingSFactor()
    {
        return blendState.getSFactor();
    }

    public int getBlendingDFactor()
    {
        return blendState.getDFactor();
    }

    //endregion

    //region Depth

    public void enableDepth()
    {
        if (!depthState.isEnabled())
            depthState.setEnabled(true);
    }

    public void disableDepth()
    {
        if (depthState.isEnabled())
            depthState.setEnabled(true);
    }

    public boolean isDepthEnabled()
    {
        return depthState.isEnabled();
    }

    //endregion

    //region Lighting

    public void enableLighting()
    {
        if (!lightingState.isEnabled())
            lightingState.setEnabled(true);
    }

    public void disableLighting()
    {
        if (lightingState.isEnabled())
            lightingState.setEnabled(false);
    }

    public boolean isLightingEnabled()
    {
        return lightingState.isEnabled();
    }

    //endregion

    //region Color

    public void setClearColor(float r, float g, float b, float a)
    {
        colorState.setColor(r, g, b, a);
    }

    public float getR()
    {
        return colorState.getR();
    }

    public float getG()
    {
        return colorState.getG();
    }

    public float getB()
    {
        return colorState.getB();
    }

    public float getA()
    {
        return colorState.getA();
    }

    //endregion

    public void resetSubStates()
    {
        cullState.resetState();
        blendState.resetState();
        depthState.resetState();
        lightingState.resetState();
    }

}
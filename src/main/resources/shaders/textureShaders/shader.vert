#version 330

in vec2 positions;
in vec2 textureCoordinates;

out vec2 outTextureCoordinates;

uniform mat4 perspectiveMatrix;
uniform mat4 translationMatrix;

void main(void)
{
    gl_Position = perspectiveMatrix * translationMatrix * vec4(positions, 0.0, 1.0);
    outTextureCoordinates = textureCoordinates;
}

#version 330

in vec2 outTextureCoordinates;

out vec4 finalColor;

uniform sampler2D fontAtlas;

void main(void)
{
    finalColor = texture(fontAtlas, outTextureCoordinates);
}
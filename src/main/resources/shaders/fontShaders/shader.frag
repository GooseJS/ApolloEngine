#version 330

in vec2 outTextureCoordinates;

out vec4 finalColor;

uniform sampler2D fontAtlas;
uniform vec3 color;

void main(void)
{
    finalColor = vec4(color, texture(fontAtlas, outTextureCoordinates).r);
}
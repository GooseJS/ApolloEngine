#version 400 core

in vec3 position;
in vec4 color;

out vec4 colour;

uniform mat4 orthoMatrix;

void main()
{

	gl_Position = orthoMatrix * vec4(position, 1.0);
	colour = color;

}
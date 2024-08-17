# Pixel Puff: A Java-Based Voxel Engine

Welcome to **Pixel Puff**, a voxel engine built entirely in Java. This project represents my journey into understanding the intricacies of voxel-based game engines, inspired by the works of industry experts.

![Logo](Logo.jpg)

## Authors

- [@paramdholakia](https://www.github.com/paramdholakia)

## Voxel and Voxel Engine Explained

### What is a Voxel?

A **voxel** (short for "volume element") is the 3D equivalent of a pixel in 2D. Just as a pixel represents a tiny part of a 2D image, a voxel represents a tiny part of a 3D space. Below is a detailed breakdown:

#### Basic Concept
- Imagine a 3D space divided into a grid, similar to how a 2D image is divided into pixels. Each small unit in this 3D grid is a voxel.
- Each voxel contains information about the properties of that part of the space, such as color, density, material type, or other attributes.

#### Representation
- Voxels are typically cubic but can represent any kind of 3D data.
- They are often used to represent volumetric data in a discrete grid, where the space is divided into regular intervals along the x, y, and z axes.

#### Applications
- **Medical Imaging**: Voxels are used in CT scans and MRIs to visualize internal structures in 3D.
- **Geographic Information Systems (GIS)**: Voxels represent 3D terrain, with each voxel corresponding to a specific volume of earth or water.
- **Computer Graphics and Games**: Voxels are used to create landscapes, buildings, and other structures, offering more detailed and complex shapes.

#### Comparison to Polygons
- Traditional 3D graphics often use polygons (typically triangles) to represent objects. Voxels, on the other hand, represent objects as a collection of small cubes, allowing for more detailed modeling at the cost of higher memory and processing requirements.

### What is a Voxel Engine?

A **voxel engine** is a type of software or framework designed to create, manipulate, and render 3D environments using voxels. Here's a deeper look:

#### Core Functionality
- **Voxel Creation and Storage**: Efficient management of a vast number of voxels, storing data like position, color, and other properties.
- **Voxel Manipulation**: Allows real-time manipulation of voxels, including adding, removing, or modifying them.
- **Rendering**: Converts voxel data into a visible 3D image, calculating which voxels are visible and how they should appear based on lighting, shading, and perspective.
- **Optimization**: Techniques like **culling** (ignoring non-visible voxels) or **level of detail (LOD)** (rendering distant objects with fewer voxels) are used to improve performance.

#### Types of Voxel Engines
- **Static Voxel Engines**: Used in applications where voxel data doesn't change frequently, such as medical imaging or geographic simulations.
- **Dynamic Voxel Engines**: Designed for environments where voxels can be added, removed, or modified in real-time, such as in many voxel-based games.

#### Rendering Techniques
- **Ray Casting/Ray Marching**: Traces rays through the voxel grid to determine what is visible on the screen, useful for accurate volume rendering.
- **Surface Extraction**: Only the surface voxels visible to the player are rendered, often using techniques like **Marching Cubes** to convert voxel data into polygons.

#### Performance Considerations
- **Memory Usage**: Storing large voxel datasets can consume significant memory, leading to the use of compression techniques or sparse voxel octrees.
- **Processing Power**: Rendering and updating large voxel worlds require significant processing power, especially for real-time applications.

#### Popular Examples
- **Minecraft**: Each block in the game is a voxel, with the engine managing rendering and interaction.
- **Ken Silverman's Voxlap Engine**: An early voxel engine used in games like **Ace of Spades**.
- **Atomontage Engine**: A modern voxel engine known for advanced rendering and simulation capabilities.

### Summary

- **Voxel**: The smallest unit in a 3D grid representing volumetric data, similar to a 3D pixel.
- **Voxel Engine**: Software that handles the creation, manipulation, and rendering of 3D environments using voxels, often used in games, simulations, and scientific visualization.

This explanation provides a comprehensive overview of voxels and voxel engines, offering insight into their core functionalities, applications, and the technical challenges involved in their use.

## Acknowledgements

This project is the result of my desire to delve into the world of Java-based engine development. I owe a great deal of my understanding and the successful creation of this voxel engine to the tutorial series by [ReonFourie](https://www.youtube.com/@ReonFourie).

Through his detailed guides, I was able to learn not only the technical aspects of creating a voxel engine but also gained a deeper appreciation for the process of building projects from the ground up. His tutorials have been instrumental in shaping my journey as an aspiring developer.

- [ReonFourie](https://www.youtube.com/@ReonFourie)
- [ReonFourie - LWJGL Voxel Engine Tutorial Series](https://www.youtube.com/playlist?list=PL80Zqpd23vJfyWQi-8FKDbeO_ZQamLKJL)

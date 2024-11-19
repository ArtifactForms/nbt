[![Java CI with Maven](https://github.com/ArtifactForms/nbt/actions/workflows/maven.yml/badge.svg)](https://github.com/ArtifactForms/nbt/actions/workflows/maven.yml)
[![CodeQL Advanced](https://github.com/ArtifactForms/nbt/actions/workflows/codeql.yml/badge.svg)](https://github.com/ArtifactForms/nbt/actions/workflows/codeql.yml)

# NBT Library

This library provides functionalities for reading, writing, and validating Named Binary Tag (NBT) files
used for data storage in Minecraft. It supports reading and writing compressed (gzip) NBT files and
writing schematic files (.schematic).

**Supported Minecraft Versions**

List -NA-

## Background / Intension

The NBT library originated as a side project during a larger Minecraft project. In 2021, I was asked by a 
building team to develop a tool that could import large 3D models (in OBJ format) from Blender directly into Minecraft worlds.

Previously, the team had tried to automate this process using the online Voxelizer from Drububu. While this tool offers the 
ability to export voxel data into Minecraft Schematics, it encounters limitations when dealing with large models. 
The restrictions regarding maximum size and block count made it necessary to split large models into smaller parts 
and individually integrate them into Minecraft - a time-consuming process.

To solve this problem, I developed a tool that transfers OBJ models directly into Minecraft worlds. To do this, 
it was necessary to convert the data into the NBT format, which Minecraft uses to store world data. The development 
of this tool led to the creation of the NBT library.

## Features

- Reading compressed (*gzip*) and uncompressed NBT files
- Writing compressed (*gzip*) NBT files
- Writing ```.schematic``` files
- Validation of ```.schematic``` files
- Traverse data structure via *TagVisitor* 

## Usage

## Reading NBT files

The following example shows how to read a compressed (gzip) NBT file:

```java
// Indicate compressed file
boolean compressed = true;
File file = new File(path);
NbtReader reader = new NbtReader(file, compressed);
Tag root = reader.read();
reader.close();
```
**Explanation:**

* ```compressed```: Set to ```true``` for a compressed file, ```false``` for uncompressed.
* ```File```: Path to the NBT file.
* ```NbtReader```: Creates a reader object for the file.
* ```read```: Reads the NBT data and stores it in the root variable.
* ```close```: Closes the reader to release resources.

**Reading uncompressed files is similar, just set compressed to ```false```.**

The equivalent to read an uncompressed NBT file:

```java
boolean compressed = false;
File file = new File(path);
NbtReader reader = new NbtReader(file, compressed);
Tag root = reader.read();
reader.close();
```
The default mode is compressed.

```java
NbtReader reader = new NbtReader();
```

## Writing Schematics

```java

// Define schematic dimensions
int width = 10;
int height = 20;
int length = 30;

// Create schematic object
Schematic schematic = Schematic.createSchematic(width, height, length);

// Set block at a specific position
schematic.setBlockAt(x, y, z, Material.WOOL_LIME);

// Write schematic data to a file
schematic.write(new File("./foo.schematic"));
```

## Visualize

```java
boolean printEndTag = true;
Tag root;
PrintPrettyTreeTagVisitor visitor = new PrintPrettyTreeTagVisitor(printEndTag);
root.accept(visitor);
System.out.println(visitor.getString());
```

**Example Result**

```
TAG_Compound('Schematic'): 15 entries
{
	TAG_Short('Width'): 15
	TAG_Short('Length'): 15
	TAG_Short('Height'): 28
	TAG_String('Materials'): Alpha
	TAG_Int('WEOriginX'): -314
	TAG_Int('WEOriginY'): 11
	TAG_Int('WEOriginZ'): -490
	TAG_Int('WEOffsetX'): -7
	TAG_Int('WEOffsetY'): 0
	TAG_Int('WEOffsetZ'): -7
	TAG_String('Platform'): bukkit
	TAG_ByteArray('Blocks'): 6300 bytes
	TAG_ByteArray('Data'): 6300 bytes
	TAG_List:COMPOUND('TileEntities'): 0 entries
	{
	}
	TAG_List:COMPOUND('Entities'): 0 entries
	{
	}
} TAG_End
```

## Schematic Validation

``` java
CompoundTag root;
SchematicValidator validator = new SchematicValidator();
SchematicValidationResult result = validator.validate(root);
boolean isInvalid = result.isInvalid();
```

## Invalid Schematic Causes

 - ```INVALID_ROOT_NAME```
 - ```MISSING_REQUIRED_ATTRIBUTE_WIDTH```
 - ```MISSING_REQUIRED_ATTRIBUTE_HEIGHT```
 - ```MISSING_REQUIRED_ATTRIBUTE_LENGTH```
 - ```MISSING_REQUIRED_ATTRIBUTE_BLOCKS```
 - ```MISSING_REQUIRED_ATTRIBUTE_DATA```
 - ```MISSING_REQUIRED_ATTRIBUTE_MATERIALS```
 - ```IF_ONE_WORLD_EDIT_INFO_IS_PROVIDED_ALL_INFOS_SHOULD_BE_PROVIDED```
 - ```TYPE_OF_WIDTH_IS_NOT_SHORT```
 - ```TYPE_OF_HEIGHT_IS_NOT_SHORT```
 - ```TYPE_OF_LENTGH_IS_NOT_SHORT```
 - ```TYPE_OF_DATA_IS_NOT_BYTE_ARRAY```
 - ```TYPE_OF_BLOCKS_IS_NOT_BYTE_ARRAY```
 - ```BLOCKS_SIZE_DOES_NOT_MATCH_VOLUME_DESCRIPTION```
 - ```DATA_SIZE_DOES_NOT_MATCH_VOLUME_DESCRIPTION```
 - ```ONE_OR_MORE_VOLUME_ATTRIBUTES_ARE_LESS_THAN_ZERO```

## Data Types
|Id|Type|Class|
|--|--|--|
|0|End|nbt.tags.EndTag|
|1|Byte|nbt.tags.ByteTag|
|2|Short|nbt.tags.ShortTag|
|3|Int|nbt.tags.IntTag|
|4|Long|nbt.tags.LongTag|
|5|Float|nbt.tags.FloatTag|
|6|Double|nbt.tags.DoubleTag|
|7|Byte-Array|nbt.tags.ByteArrayTag|
|8|String|nbt.tags.StringTag|
|9|List|nbt.tags.ListTag|
|10|Compound|nbt.tags.CompoundTag|
|11|Int-Array|nbt.tags.IntArrayTag|
|12|Long-Array|nbt.tags.LongArrayTag|



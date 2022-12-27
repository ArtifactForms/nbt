# nbt
NBT file library

# Features

- Reading compressed and uncompressed NBT files
- Writing NBT files
- Writing ```.schematic``` files
- Validation of ```.schematic``` files

# Usage

## Writing

```java
Schematic schematic = Schematic.createSchematic(width, height, length);
schematic.setBlockAt(x, y, z, Material.WOOL_LIME);
schematic.write(new File("./foo.schematic"));
```

## Reading

```java
boolean compressed = true;
File file = new File(path);
NbtReader reader = new NbtReader(file, compressed);
Tag root = reader.read();
reader.close();
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

# Validation

``` java
CompoundTag root;
SchematicValidator validator = new SchematicValidator();
SchematicValidationResult result = validator.validate(root);
boolean isInvalid = result.isInvalid();
```

## Invalid Schematic Causes

 - INVALID_ROOT_NAME
 - MISSING_REQUIRED_ATTRIBUTE_WIDTH
 - MISSING_REQUIRED_ATTRIBUTE_HEIGHT
 - MISSING_REQUIRED_ATTRIBUTE_LENGTH
 - MISSING_REQUIRED_ATTRIBUTE_BLOCKS
 - MISSING_REQUIRED_ATTRIBUTE_DATA
 - MISSING_REQUIRED_ATTRIBUTE_MATERIALS
 - IF_ONE_WORLD_EDIT_INFO_IS_PROVIDED_ALL_INFOS_SHOULD_BE_PROVIDED
 - TYPE_OF_WIDTH_IS_NOT_SHORT,
 - TYPE_OF_HEIGHT_IS_NOT_SHORT,
 - TYPE_OF_LENTGH_IS_NOT_SHORT,
 - TYPE_OF_DATA_IS_NOT_BYTE_ARRAY,
 - TYPE_OF_BLOCKS_IS_NOT_BYTE_ARRAY,
 - BLOCKS_SIZE_DOES_NOT_MATCH_VOLUME_DESCRIPTION,
 - DATA_SIZE_DOES_NOT_MATCH_VOLUME_DESCRIPTION,
 - ONE_OR_MORE_VOLUME_ATTRIBUTES_ARE_LESS_THAN_ZERO


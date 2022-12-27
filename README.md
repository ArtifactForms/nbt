# nbt
NBT file library

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
Tag root;
PrintPrettyTreeTagVisitor visitor = new PrintPrettyTreeTagVisitor(true);
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

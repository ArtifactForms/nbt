package nbt.visitor;

import nbt.tags.ByteArrayTag;
import nbt.tags.ByteTag;
import nbt.tags.CompoundTag;
import nbt.tags.DoubleTag;
import nbt.tags.EndTag;
import nbt.tags.FloatTag;
import nbt.tags.IntArrayTag;
import nbt.tags.IntTag;
import nbt.tags.ListTag;
import nbt.tags.LongArrayTag;
import nbt.tags.LongTag;
import nbt.tags.ShortTag;
import nbt.tags.StringTag;

public interface TagVisitor {

    void visit(ByteArrayTag tag);

    void visit(ByteTag tag);

    void visit(DoubleTag tag);

    void visit(FloatTag tag);

    void visit(IntTag tag);

    void visit(LongTag tag);

    void visit(ShortTag tag);

    void visit(IntArrayTag tag);

    void visit(StringTag tag);

    void visit(CompoundTag tag);

    void visit(ListTag tag);

    void visit(LongArrayTag tag);

    void visit(EndTag tag);

    void onEndList(ListTag tag);

}

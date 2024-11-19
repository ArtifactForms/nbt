package tests;

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
import nbt.visitor.TagVisitor;

public class TestCountVisitsVisitor implements TagVisitor {

    private int visitedTotal;
    
    private int visitedByteArrayTagCount;
    
    private int visitedByteTagCount;
    
    private int visitedDoubleTagCount;
    
    private int visitedFloatTagCount;
    
    private int visitedIntTagCount;
    
    private int visitedLongTagCount;
    
    private int visitedShortTagCount;
    
    private int visitedInArrayTagCount;
    
    private int visitedStringTagCount;
    
    private int visitedCompoundTagCount;
    
    private int visitedListTagCount;
    
    private int visitedLongArrayTagCount;
    
    private int visitedEndTagCount;

    private void incrementTotal() {
        visitedTotal++;
    }

    @Override
    public void visit(ByteArrayTag tag) {
        visitedByteArrayTagCount++;
        incrementTotal();
    }

    @Override
    public void visit(ByteTag tag) {
        visitedByteTagCount++;
        incrementTotal();
    }

    @Override
    public void visit(DoubleTag tag) {
        visitedDoubleTagCount++;
        incrementTotal();
    }

    @Override
    public void visit(FloatTag tag) {
        visitedFloatTagCount++;
        incrementTotal();
    }

    @Override
    public void visit(IntTag tag) {
        visitedIntTagCount++;
        incrementTotal();
    }

    @Override
    public void visit(LongTag tag) {
        visitedLongTagCount++;
        incrementTotal();
    }

    @Override
    public void visit(ShortTag tag) {
        visitedShortTagCount++;
        incrementTotal();
    }

    @Override
    public void visit(IntArrayTag tag) {
        visitedInArrayTagCount++;
        incrementTotal();
    }

    @Override
    public void visit(StringTag tag) {
        visitedStringTagCount++;
        incrementTotal();
    }

    @Override
    public void visit(CompoundTag tag) {
        visitedCompoundTagCount++;
        incrementTotal();
    }

    @Override
    public void visit(ListTag tag) {
        visitedListTagCount++;
        incrementTotal();
    }

    @Override
    public void visit(LongArrayTag tag) {
        visitedLongArrayTagCount++;
        incrementTotal();
    }

    @Override
    public void visit(EndTag tag) {
        visitedEndTagCount++;
        incrementTotal();
    }

    @Override
    public void onEndList(ListTag tag) {
        // TODO Auto-generated method stub

    }

    public int getVisitedTotal() {
        return visitedTotal;
    }

    public int getVisitedByteArrayTagCount() {
        return visitedByteArrayTagCount;
    }

    public int getVisitedByteTagCount() {
        return visitedByteTagCount;
    }

    public int getVisitedDoubleTagCount() {
        return visitedDoubleTagCount;
    }

    public int getVisitedFloatTagCount() {
        return visitedFloatTagCount;
    }

    public int getVisitedIntTagCount() {
        return visitedIntTagCount;
    }

    public int getVisitedLongTagCount() {
        return visitedLongTagCount;
    }

    public int getVisitedShortTagCount() {
        return visitedShortTagCount;
    }

    public int getVisitedInArrayTagCount() {
        return visitedInArrayTagCount;
    }

    public int getVisitedStringTagCount() {
        return visitedStringTagCount;
    }

    public int getVisitedCompoundTagCount() {
        return visitedCompoundTagCount;
    }

    public int getVisitedListTagCount() {
        return visitedListTagCount;
    }

    public int getVisitedLongArrayTagCount() {
        return visitedLongArrayTagCount;
    }

    public int getVisitedEndTagCount() {
        return visitedEndTagCount;
    }

}

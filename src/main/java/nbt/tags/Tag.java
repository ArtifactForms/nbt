package nbt.tags;

import java.util.ArrayList;
import java.util.List;

import nbt.exception.NbtException;
import nbt.visitor.TagVisitor;

public abstract class Tag {

    public static final String EMPTY_STRING = "";

    private Tag parent;

    private String name;

    private List<Tag> tags;

    public Tag(String name) {
        this.name = name;
        tags = new ArrayList<Tag>();
    }

    public abstract void accept(TagVisitor visitor);

    public abstract NbtTagType getType();

    public int getTagCountExceptEndTags() {
        int count = 0;
        for (Tag tag : tags) {
            if (tag.getType() != NbtTagType.END)
                count++;
        }
        return count;
    }

    public Tag getTagByName(String name) {
        if (this.name.equals(name))
            return this;

        if (!allowsChildren())
            return null;

        for (Tag tag : tags) {
            Tag result = tag.getTagByName(name);
            if (result != null)
                return result;
        }

        return null;
    }

    public int getIndexOf(Tag tag) {
        return tags.indexOf(tag);
    }

    public boolean isList() {
        return false;
    }

    public void add(Tag tag) {
        if (tag == null) {
            throw new NbtException("Adding null elements is not allowed.");
        }

        if (!allowsChildren()) {
            throw new NbtException("This tag does not allow child elements.");
        }

        tag.setParent(this);
        tags.add(tag);
    }

    public void remove(Tag tag) {
        if (!allowsChildren())
            throw new NbtException(
                    "This operation is not supported for this kind of tag (allowsChildren == false)."
            );

        if (tag == null)
            return;

        if (!tags.contains(tag))
            return;

        tag.setParent(null);
        tags.remove(tag);
    }

    public int getTagCount() {
        return tags.size();
    }

    public Tag getTagAt(int index) {
        return tags.get(index);
    }

    public boolean isLeaf() {
        if (!allowsChildren())
            return true;

        return tags.size() == 0;
    }

    public boolean allowsChildren() {
        return false;
    }

    public Tag getBranch() {
        if (parent == null) {
            if (allowsChildren())
                return this;
            throw new NbtException(
                    "Parent is null. And this tag is not a valid branch."
            );
        }
        return parent;
    }

    public Tag getRoot() {
        if (parent == null)
            return this;
        return getParent().getRoot();
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public Tag getParent() {
        return parent;
    }

    protected void setParent(Tag parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

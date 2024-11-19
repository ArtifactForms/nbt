package nbt.io;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

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
import nbt.tags.NbtTagType;
import nbt.tags.ShortTag;
import nbt.tags.StringTag;
import nbt.tags.Tag;
import nbt.visitor.TagVisitor;

public class NbtWriter implements Closeable {

	private DataOutputStream out;

	public NbtWriter(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		GZIPOutputStream gos = new GZIPOutputStream(fos);
		out = new DataOutputStream(gos);
	}

	public void write(Tag root) throws IOException {
		try {
			WriteTagVisitor visitor = new WriteTagVisitor();
			root.accept(visitor);
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	public void close() throws IOException {
		out.close();
	}

	private void writeDouble(double value) throws IOException {
		out.writeDouble(value);
	}

	private void writeByte(byte value) throws IOException {
		out.writeByte(value);
	}

	private void writeInt(int value) throws IOException {
		out.writeInt(value);
	}

	private void writeFloat(float value) throws IOException {
		out.writeFloat(value);
	}

	private void writeLongs(long value) throws IOException {
		out.writeLong(value);
	}

	private void writeShort(short value) throws IOException {
		out.writeShort(value);
	}

	private void writeString(String value) throws IOException {
		out.writeUTF(value);
	}

	private void writeLongArray(long[] value) throws IOException {
		out.writeInt(value.length);
		for (int i = 0; i < value.length; i++) {
			out.writeLong(value[i]);
		}
	}

	private void writeIntArray(int[] value) throws IOException {
		out.writeInt(value.length);
		for (int i = 0; i < value.length; i++) {
			out.writeInt(value[i]);
		}
	}

	private void writeByteArray(byte[] value) throws IOException {
		out.writeInt(value.length);
		for (int i = 0; i < value.length; i++) {
			out.writeByte(value[i]);
		}
	}

	private void writeIdAndNameExceptListElements(Tag tag) throws IOException {
		if (parentIsList(tag))
			return;
		writeByte(tag.getType().getId());
		writeString(tag.getName());
	}

	private boolean parentIsList(Tag tag) {
		return tag.hasParent() && tag.getParent().isList();
	}

	private void writeEnd() throws IOException {
		out.writeByte(NbtTagType.END.getId());
	}

	private class WriteTagVisitor implements TagVisitor {

		@Override
		public void visit(ByteArrayTag tag) {
			try {
				writeIdAndNameExceptListElements(tag);
				writeByteArray(tag.getValue());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void visit(ByteTag tag) {
			try {
				writeIdAndNameExceptListElements(tag);
				writeByte(tag.getValue());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void visit(DoubleTag tag) throws RuntimeException {
			try {
				writeIdAndNameExceptListElements(tag);
				writeDouble(tag.getValue());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void visit(FloatTag tag) {
			try {
				writeIdAndNameExceptListElements(tag);
				writeFloat(tag.getValue());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void visit(IntTag tag) {
			try {
				writeIdAndNameExceptListElements(tag);
				writeInt(tag.getValue());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void visit(LongTag tag) {
			try {
				writeIdAndNameExceptListElements(tag);
				writeLongs(tag.getValue());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void visit(ShortTag tag) {
			try {
				writeIdAndNameExceptListElements(tag);
				writeShort(tag.getValue());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void visit(IntArrayTag tag) {
			try {
				writeIdAndNameExceptListElements(tag);
				writeIntArray(tag.getValue());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void visit(StringTag tag) {
			try {
				writeIdAndNameExceptListElements(tag);
				writeString(tag.getValue());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void visit(CompoundTag tag) {
			try {
				if (parentIsList(tag))
					return;
				writeIdAndNameExceptListElements(tag);
			} catch (IOException e) {

				throw new RuntimeException(e);
			}
		}

		@Override
		public void visit(LongArrayTag tag) {
			try {
				writeIdAndNameExceptListElements(tag);
				writeLongArray(tag.getValue());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void validateList(ListTag tag) {
			if (tag.getContentType() == NbtTagType.END && tag.getSize() > 0) {
				throw new RuntimeException("List content type END but size greater 0." + " List: " + tag.getName());
			}
		}

		@Override
		public void visit(ListTag tag) {
			try {
				validateList(tag);
				writeIdAndNameExceptListElements(tag);
				writeByte(tag.getContentType().getId());
				writeInt(tag.getSize());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void visit(EndTag tag) {
			try {
				writeEnd();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void onEndList(ListTag tag) {
			// TODO Auto-generated method stub

		}

	}

}

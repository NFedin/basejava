import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOfRange;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int length = 0;

    void clear() {
        for (int i = 0; i < length; i++) {
            storage[i] = null;
        }
        length = 0;
    }

    void save(Resume r) {
        if (findResumeIndex(r.uuid) == -1) {
            storage[length] = r;
            length++;
        }
    }

    Resume get(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            return null;
        }
    }

    void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index != -1) {
            length--;
            arraycopy(storage, index + 1, storage, index, length - index);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return copyOfRange(storage, 0, length);
    }

    int size() {
        return length;
    }

    private int findResumeIndex(String uuid) {
        for (int i = 0; i < length; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        return -1;
    }
}

package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

import static java.lang.System.arraycopy;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void update(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index < length) {
            storage[index] = r;
        } else {
            System.out.println("ERROR: This resume not exist");
        }
    }

    @Override
    public void save(Resume r) {
        int index = findResumeIndex(r.getUuid());
        if (index < length) {
            if (length < STORAGE_LIMIT) {
                arraycopy(storage, index, storage, index + 1, length - index);
                storage[length] = r;
                length++;
            } else {
                System.out.println("ERROR: overflow storage");
            }
        } else {
            System.out.println("ERROR: This resume exist");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = findResumeIndex(uuid);
        if (index < length) {
            arraycopy(storage, index + 1, storage, index, length - index);
            length--;
        } else {
            System.out.println("ERROR: This resume not exist");
        }
    }


    @Override
    protected int findResumeIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, length, searchKey);
    }
}

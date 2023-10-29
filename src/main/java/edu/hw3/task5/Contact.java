package edu.hw3.task5;

import java.util.Arrays;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Contact implements Comparable<Contact> {
    private @NotNull String name;
    private @Nullable String secondName;

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Contact contact = (Contact) o;

        if (!name.equals(contact.name)) {
            return false;
        }
        if (!Objects.equals(secondName, contact.secondName)) {
            return false;
        }
        return Objects.equals(surname, contact.surname);
    }

    @Override public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    private @Nullable String surname;

    public Contact(@NotNull String stringContact) {
        String[] parts = stringContact.split(" ");
        this.name = parts[0];
        if (parts.length > 1) {
            this.surname = parts[parts.length - 1];
        }
        if (parts.length > 2) {
            this.secondName = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length - 1));
        }
    }

    @Override public int compareTo(@NotNull Contact o) {
        int compareMain = mainPart().compareTo(o.mainPart());
        if (compareMain == 0) {
            return name.compareTo(o.name);
        }
        return compareMain;
    }

    private String mainPart() {
        return surname == null ? name : surname;
    }

    @Override public String toString() {
        if (secondName == null && surname == null) {
            return name;
        } else if (secondName == null) {
            return name + " " + surname;
        } else {
            return name + " " + secondName + " " + surname;
        }
    }

    public @NotNull String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public @Nullable String getSecondName() {
        return secondName;
    }

    public void setSecondName(@Nullable String secondName) {
        this.secondName = secondName;
    }

    public @Nullable String getSurname() {
        return surname;
    }

    public void setSurname(@Nullable String surname) {
        this.surname = surname;
    }
}

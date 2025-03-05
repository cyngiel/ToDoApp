package com.ps.todoapp.utils;

import java.util.Optional;

/**
 * Utility class providing methods for working with Enums.
 */
public class EnumUtils {

    /**
     * Attempts to convert a string value to an enum constant of the specified enum type.
     * <p>
     * This method calls {@link Enum#valueOf(Class, String)} to retrieve the enum constant,
     * and if the provided value does not correspond to any enum constant, it catches
     * the {@link IllegalArgumentException} and returns an empty {@link Optional}.
     *
     * @param <E> the enum type to which the value should be mapped
     * @param enumClass the {@link Class} object of the enum type
     * @param value the string value representing an enum constant
     * @return an {@link Optional} containing the corresponding enum constant,
     *         or an empty {@link Optional} if no matching constant is found
     */
    public static <E extends Enum<E>> Optional<E> getEnumFromString(Class<E> enumClass, String value) {
        try {
            return Optional.of(Enum.valueOf(enumClass, value));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}

/*
 * This file is part of StarNub.
 *
 * StarNub is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. StarNub is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with StarNub.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * This file is part of StarNub.
 *
 * StarNub is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. StarNub is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with StarNub.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * This file is part of StarNub.
 *
 * StarNub is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. StarNub is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with StarNub.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.starnub.util;

import org.apache.commons.lang.Validate;

import java.util.Collection;

public class StringUtil {

    /**
     * Copies all elements from the iterable collection of originals to the
     * collection provided.
     *
     * @param token String to search for
     * @param originals An iterable collection of strings to filter.
     * @param collection The collection to add matches to
     * @return the collection provided that would have the elements copied
     *     into
     * @throws UnsupportedOperationException if the collection is immutable
     *     and originals contains a string which starts with the specified
     *     search string.
     * @throws IllegalArgumentException if any parameter is is null
     * @throws IllegalArgumentException if originals contains a null element.
     *     <b>Note: the collection may be modified before this is thrown</b>
     */
    public static <T extends Collection<? super String>> T copyPartialMatches(final String token, final Iterable<String> originals, final T collection) throws UnsupportedOperationException, IllegalArgumentException {
        Validate.notNull(token, "Search token cannot be null");
        Validate.notNull(collection, "Collection cannot be null");
        Validate.notNull(originals, "Originals cannot be null");

        for (String string : originals) {
            if (startsWithIgnoreCase(string, token)) {
                collection.add(string);
            }
        }

        return collection;
    }

    /**
     * This method uses a region to check case-insensitive equality. This
     * means the internal array does not need to be copied like a
     * toLowerCase() call would.
     *
     * @param string String to check
     * @param prefix Prefix of string to compare
     * @return true if provided string starts with, ignoring case, the prefix
     *     provided
     * @throws NullPointerException if prefix is null
     * @throws IllegalArgumentException if string is null
     */
    public static boolean startsWithIgnoreCase(final String string, final String prefix) throws IllegalArgumentException, NullPointerException {
        Validate.notNull(string, "Cannot check a null string for a match");
        if (string.length() < prefix.length()) {
            return false;
        }
        return string.regionMatches(true, 0, prefix, 0, prefix.length());
    }
}

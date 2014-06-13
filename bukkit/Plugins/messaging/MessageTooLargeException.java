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

package org.starnub.plugins.messaging;

/**
 * Thrown if a Plugin Message is sent that is too large to be sent.
 */
@SuppressWarnings("serial")
public class MessageTooLargeException extends RuntimeException {
    public MessageTooLargeException() {
        this("Attempted to send a plugin message that was too large. The maximum length a plugin message may be is " + Messenger.MAX_MESSAGE_SIZE + " bytes.");
    }

    public MessageTooLargeException(byte[] message) {
        this(message.length);
    }

    public MessageTooLargeException(int length) {
        this("Attempted to send a plugin message that was too large. The maximum length a plugin message may be is " + Messenger.MAX_MESSAGE_SIZE + " bytes (tried to send one that is " + length + " bytes long).");
    }

    public MessageTooLargeException(String msg) {
        super(msg);
    }
}

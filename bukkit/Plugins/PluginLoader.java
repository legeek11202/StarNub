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

package org.starnub.plugins;

import java.io.File;
import java.util.regex.Pattern;

/**
 * Represents a plugin loader, which handles direct access to specific types
 * of plugins
 */
public interface PluginLoader {

    /**
     * Loads the plugin contained in the specified file
     *
     * @param file File to attempt to load
     * @return Plugin that was contained in the specified file, or null if
     *     unsuccessful
     * @throws InvalidPluginException Thrown when the specified file is not a
     *     plugin
     * @throws UnknownDependencyException If a required dependency could not
     *     be found
     */
    public Plugin loadPlugin(File file) throws InvalidPluginException, UnknownDependencyException;

    /**
     * Loads a PluginDescriptionFile from the specified file
     *
     * @param file File to attempt to load from
     * @return A new PluginDescriptionFile loaded from the plugin.yml in the
     *     specified file
     * @throws InvalidDescriptionException If the plugin description file
     *     could not be created
     */
    public PluginDescriptionFile getPluginDescription(File file) throws InvalidDescriptionException;

    /**
     * Returns a list of all filename filters expected by this PluginLoader
     *
     * @return The filters
     */
    public Pattern[] getPluginFileFilters();

    /**
     * Enables the specified plugin
     * <p>
     * Attempting to enable a plugin that is already enabled will have no
     * effect
     *
     * @param plugin Plugin to enable
     */
    public void enablePlugin(Plugin plugin);

    /**
     * Disables the specified plugin
     * <p>
     * Attempting to disable a plugin that is not enabled will have no effect
     *
     * @param plugin Plugin to disable
     */
    public void disablePlugin(Plugin plugin);
}

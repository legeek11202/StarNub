//package org.starnub;
//
//import com.avaje.ebean.config.ServerConfig;
//
//
//import java.io.Console;
//import java.io.File;
//import java.util.logging.Logger;
//
////TODOS Combine with old server variables, messaging, commands ect, players max, players online
//
//
///**
// * Represents a server implementation.
// */
//public interface Server {
//
//
//    public Console getConsole();
//
//    /**
//     * Gets the name of this server implementation.
//     *
//     * @return name of this server implementation
//     */
//    public String getName();
//
//    /**
//     * Gets the version string of this server implementation.
//     *
//     * @return version of this server implementation
//     */
//    public String getStarboundVersion();
//
//    /**
//     * Gets the StarNub version that this server is running.
//     *
//     * @return version of StarNub
//     */
//    public String getStarNubVersion();
//
//    /**
//     * Get the game port that the server runs on.
//     *
//     * @return the port number of this server
//     */
//    public int getPort();
//
//    /**
//     * Get the IP that this server is bound to, or empty string if not
//     * specified.
//     *
//     * @return the IP string that this server is bound to, otherwise empty
//     *     string
//     */
//    public String getIp();
//
//    /**
//     * Get the name of this server.
//     *
//     * @return the name of this server
//     */
//    public String getServerName();
//
//    /**
//     * Get an ID of this server. The ID is a simple generally alphanumeric ID
//     * that can be used for uniquely identifying this server.
//     *
//     * @return the ID of this server
//     */
//    public String getServerId();
//
//    /**
//     * Gets the name of the update folder. The update folder is used to safely
//     * update plugins at the right moment on a plugin load.
//     * <p>
//     * The update folder name is relative to the plugins folder.
//     *
//     * @return the name of the update folder
//     */
//    public String getUpdateFolder();
//
//    /**
//     * Gets the update folder. The update folder is used to safely update
//     * plugins at the right moment on a plugin load.
//     *
//     * @return the update folder
//     */
//    public File getUpdateFolderFile();
//
//
//    /**
//     * Gets the plugin manager for interfacing with plugins.
//     *
//     * @return a plugin manager for this Server instance
//     */
////    public SimplePluginManager getPluginManager();
//
//    /**
//     * Reloads the server, refreshing settings and plugin information.
//     */
//    public void reload();
//
//    /**
//     * Returns the primary logger associated with this server instance.
//     *
//     * @return Logger associated with this server
//     */
//    public Logger getLogger();
//
//
//    /**
//     * Populates a given {@link ServerConfig} with values attributes to this
//     * server.
//     *
//     * @param config the server config to populate
//     */
//    public void configureDbConfig(ServerConfig config);
//
//    /**
//     * Shutdowns the server, stopping everything.
//     */
//    public void shutdown();
//
//    /**
//     * Checks the current thread against the expected primary thread for the
//     * server.
//     * <p>
//     * <b>Note:</b> this method should not be used to indicate the current
//     * synchronized state of the runtime. A current thread matching the main
//     * thread indicates that it is synchronized, but a mismatch <b>does not
//     * preclude</b> the same assumption.
//     *
//     * @return true if the current thread matches the expected primary thread,
//     *     false otherwise
//     */
//    boolean isPrimaryThread();
//
//}

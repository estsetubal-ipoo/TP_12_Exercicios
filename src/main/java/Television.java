import java.util.ArrayList;
import java.util.List;

/**
 * Represents a television with a list of channels. Provides functionalities
 * to add, remove, and manage channels, including tuning to specific channels,
 * swapping them, and toggling favorites.
 */
public class Television {

    /**
     * Factory default channels for reference. These channels are used to reset
     * the television to its factory settings.
     */
    private static Channel[] factoryChannels = {
            new Channel("RTP 1 HD", 54),
            new Channel("CM TV HD", 62),
            new Channel("SPORT.TV + HD", 78),
            new Channel("Canal 11 HD", 86),
            new Channel("Globo Portugal HD", 174),
            new Channel("TVI Reality HD", 182),
            new Channel("SIC Mulher HD", 190),
            new Channel("SIC Caras HD", 198),
            new Channel("SIC Radical HD", 206),
            new Channel("Discovery Channel HD", 470)
    };

    private List<Channel> channelList;
    private int currentPosition;

    /**
     * Constructs a new Television instance with factory default channels.
     */
    public Television() {
        channelList = new ArrayList<>();
        factorySettings();
    }

    /**
     * Validates whether the given position is within the range of the channel list.
     *
     * @param position The position to validate.
     * @return {@code true} if the position is valid, otherwise {@code false}.
     */
    private boolean isPositionValid(int position) {
        return position >= 0 && position < getNumberOfChannels();
    }

    /**
     * Checks if the television is tuned to a channel.
     *
     * @return {@code true} if a channel is currently tuned, otherwise {@code false}.
     */
    public boolean isTuned() {
        return currentPosition != -1;
    }

    /**
     * Returns the number of channels currently available.
     *
     * @return The number of channels.
     */
    public int getNumberOfChannels() {
        return channelList.size();
    }

    /**
     * Tunes the television to the channel at the specified position.
     *
     * @param position The position of the channel to tune to.
     * @return {@code true} if the channel was successfully tuned, otherwise {@code false}.
     */
    public boolean tunePosition(int position) {
        if (!isPositionValid(position)) return false;
        currentPosition = position;
        return true;
    }

    /**
     * Toggles the favorite status of the currently tuned channel.
     *
     * @return {@code true} if the favorite status was successfully toggled, otherwise {@code false}.
     */
    public boolean toggleFavorite() {
        if (!isTuned()) return false;
        channelList.get(currentPosition).toggleFavorite();
        return true;
    }

    /**
     * Adds a new channel to the television.
     * There cannot exist channels with the same frequency.
     *
     * @param channel The channel to add.
     * @return {@code true} if the channel was successfully added, otherwise {@code false}.
     */
    public boolean addChannel(Channel channel) {
        if (channel == null) return false;
        // TODO: check overlapping frequencies
        channelList.add(channel);
        return true;
    }

    /**
     * Removes the channel at the specified position.
     *
     * @param position The position of the channel to remove.
     * @return {@code true} if the channel was successfully removed, otherwise {@code false}.
     */
    public boolean removeChannel(int position) {
        if (!isPositionValid(position)) return false;
        if (currentPosition == position) {
            currentPosition = -1;
        }
        channelList.remove(position);
        return true;
    }

    /**
     * Finds the position of a channel based on its name or partial name.
     *
     * @param nameQuery The name or partial name to search for.
     * @return The position of the channel if found, otherwise -1.
     */
    public int findChannelPosition(String nameQuery) {
        if (nameQuery == null || nameQuery.isBlank()) {
            return -1;
        }
        nameQuery = nameQuery.toLowerCase();
        for (int i = 0; i < channelList.size(); i++) {
            Channel channel = channelList.get(i);
            if (channel.getName().toLowerCase().contains(nameQuery)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Swaps the positions of two channels in the list.
     *
     * @param position1 The position of the first channel.
     * @param position2 The position of the second channel.
     * @return {@code true} if the channels were successfully swapped, otherwise {@code false}.
     */
    public boolean swapChannels(int position1, int position2) {
        if (!isPositionValid(position1) || !isPositionValid(position2)) {
            return false;
        }
        Channel aux = channelList.get(position1);
        channelList.set(position1, channelList.get(position2));
        channelList.set(position2, aux);
        return true;
    }

    /**
     * Retrieves the channel at the specified position.
     *
     * @param position The position of the channel to retrieve.
     * @return The channel at the specified position, or {@code null} if the position is invalid.
     */
    public Channel getChannel(int position) {
        if (!isPositionValid(position)) {
            return null;
        }
        return channelList.get(position);
    }

    /**
     * Returns a string representation of the television, including the current position,
     * current channel, and the number of channels.
     *
     * @return A formatted string representing the television's state.
     */
    @Override
    public String toString() {
        String currentChannel = isTuned() ? getChannel(currentPosition).toString() : "None";
        return String.format("Television[currentPosition=%d, currentChannel=%s, numberChannels=%d]",
                currentPosition, currentChannel, getNumberOfChannels());
    }

    /**
     * Generates a formatted list of all channels, showing their positions.
     *
     * @return A formatted string containing the list of channels.
     */
    public String channelList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < channelList.size(); i++) {
            sb.append(String.format("%3d. %s\n", (i + 1), channelList.get(i)));
        }
        return sb.toString();
    }

    /**
     * Resets the television to its factory settings, clearing all channels and
     * loading the default set.
     */
    public void factorySettings() {
        channelList.clear();
        currentPosition = -1;
        for (Channel ch : factoryChannels) {
            if (ch.isFavorite()) {
                ch.toggleFavorite();
            }
            channelList.add(ch);
        }
    }
}

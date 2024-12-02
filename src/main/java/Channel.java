public class Channel {

    private final String name;

    /**
     * Frequency of the channel, must be in [64, 608]
     */
    private final int frequency;

    private boolean isFavorite;

    public Channel(String name, int frequency) {
        boolean validFrequency =
                frequency >= 54 && frequency <= 88
                || frequency >= 174 && frequency <= 216
                || frequency >= 470 && frequency <= 608;

        if(!validFrequency) {
            throw new IllegalArgumentException("Invalid frequency.");
        }

        this.frequency = frequency;
        this.name = name;
        isFavorite = false;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getName() {
        return name;
    }

    public Band getBand() {
        if(frequency >= 54 && frequency <= 88) {
            return Band.VHF_LOW;
        } else if(frequency >= 174 && frequency <= 216) {
            return Band.VHF_HIGH;
        } else if(frequency >= 470 && frequency <= 608) {
            return Band.UHF;
        } else {
            return Band.UNKWOWN;
        }
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
    }

    public String toString() {
        return String.format("Channel[name=%s, frequency=%dMhz, band=%s, isFavorite=%s]",
                name, frequency, getBand(), (isFavorite ? "true" : "false"));
    }

    /**
     * Test program for Channel instance creation
     * @param args
     */
    public static void main(String[] args) {
        Channel rtp1 = new Channel("RTP 1 HD", 54);
        System.out.println(rtp1);
        rtp1.toggleFavorite();
        System.out.println(rtp1);
    }
}

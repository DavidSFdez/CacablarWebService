package uo.sdi.client;

public enum TripStatus {
    OPEN, CLOSED, CANCELLED, DONE;

    public boolean isOpen() {
	return this.equals(TripStatus.OPEN);
    }
}

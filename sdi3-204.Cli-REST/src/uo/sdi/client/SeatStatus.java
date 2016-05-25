package uo.sdi.client;

public enum SeatStatus {
    ADMITIDO, EXCLUIDO, PENDIENTE, SIN_PLAZA;

    public boolean isAccepted()
    {
        return this.equals(SeatStatus.ADMITIDO);
    }
}



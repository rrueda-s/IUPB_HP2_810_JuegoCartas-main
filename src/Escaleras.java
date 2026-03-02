public enum Escaleras {
    ESCALERA3,
    ESCALERA4,
    ESCALERA5,
    ESCALERA6,
    ESCALERA7,
    ESCALERA8,
    ESCALERA9,
    ESCALERA10;

    public static Escaleras obtenerTipo(int cantidad) {

        switch (cantidad) {
            case 3: return ESCALERA3;
            case 4: return ESCALERA4;
            case 5: return ESCALERA5;
            case 6: return ESCALERA6;
            case 7: return ESCALERA7;
            case 8: return ESCALERA8;
            case 9: return ESCALERA9;
            case 10: return ESCALERA10;
            default: return null;
        }
    }
}

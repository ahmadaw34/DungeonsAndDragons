public class Range {

    public static int range(Tile t1, Tile t2) {
        return (int) Math.sqrt((t2.getY() - t1.getY()) * (t2.getY() - t1.getY()) + (t2.getX() - t1.getX()) * (t2.getX() - t1.getX()));
    }

}

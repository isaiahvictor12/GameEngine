

public class Text {
    String msg; // message to display
    int x; // x position
    int y; // y position
    boolean active; // should the text be drawn
    int size; // font size

    public Text(String _msg, int _x, int _y, boolean _active, int _size){
        msg = _msg;
        x = _x;
        y = _y;
        active = _active;
        size = _size;
    }
}

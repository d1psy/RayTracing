package diploma.geometry;

public class Canvas {

    private int width;
    private int height;
    private Color[][] colors;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        colors = new Color[width][height];
        for(int i = 0; i< width; i++) {
            for(int j = 0; j < height; j++) {
                colors[i][j] = new Color();
            }
        }
    }

    public String toPPM() {
        StringBuilder sb = new StringBuilder();
        sb.append("P3");
        sb.append(System.lineSeparator());
        sb.append(width).append(" ").append(height);
        sb.append(System.lineSeparator());
        sb.append("255");
        sb.append(System.lineSeparator());
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(getColorCode(colors[j][i].getX())).append(" ")
                        .append(getColorCode(colors[j][i].getY())).append(" ")
                        .append(getColorCode(colors[j][i].getZ())).append(" ");
            }
//            sb.deleteCharAt(sb.length() - 1);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public int getColorCode(float color) {
        if (color > 1.0f) {
            return 255;
        } else if (color < 0.0f) {
            return 0;
        }
        return (int) (255 * color);
    }

    public void writeToPixel(int width, int height, Color color) {
        if ((width < this.width && !(width < 0)) && (height < this.height && !(height < 0))) {
            colors[width][height] = color;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color[][] getColors() {
        return colors;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColors(Color[][] colors) {
        this.colors = colors;
    }
}

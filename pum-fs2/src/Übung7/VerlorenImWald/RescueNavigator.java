package Übung7.VerlorenImWald;

public class RescueNavigator {
    private ForestField[][] map;

    public RescueNavigator(int size) {
        map = new ForestField[size][size];
    }

    public ForestField[][] getMap() {
        return map;
    }

    public void createMap(String coordinates) {
        String[] pairs = coordinates.split("\\s+");
        for (String pair : pairs) {
            String[] points = pair.split("->");
            String[] start = points[0].split(",");
            String[] end = points[1].split(",");
            int startX = Integer.parseInt(start[0]);
            int startY = Integer.parseInt(start[1]);
            int endX = Integer.parseInt(end[0]);
            int endY = Integer.parseInt(end[1]);

            if (startX == endX) {
                for (int i = startY; i <= endY; i++) {
                    map[i][startX] = ForestField.TREE;
                }
            } else if (startY == endY) {
                for (int i = startX; i <= endX; i++) {
                    map[startY][i] = ForestField.TREE;
                }
            }
        }

        int size = map.length;
        map[0][0] = ForestField.RESCUE;
        map[size - 1][size - 1] = ForestField.HIKER;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == null) {
                    map[i][j] = ForestField.MOSS;
                }
            }
        }
    }

    public void printMap() {
        for (ForestField[] row : map) {
            for (ForestField field : row) {
                switch (field) {
                    case MOSS:
                        System.out.print(".");
                        break;
                    case TREE:
                        System.out.print("|");
                        break;
                    case PATH:
                        System.out.print("-");
                        break;
                    case HIKER:
                        System.out.print("%");
                        break;
                    case RESCUE:
                        System.out.print("~");
                        break;
                }
            }
            System.out.println();
        }
    }

    public void estimatePath(int startX, int startY) {
        if (startX < 0 || startY < 0 || startX >= map.length || startY >= map.length || map[startX][startY] == ForestField.TREE || map[startX][startY] == ForestField.PATH) {
            return;
        }

        map[startX][startY] = ForestField.PATH;

        estimatePath(startX + 1, startY); // move right
        estimatePath(startX - 1, startY); // move left
        estimatePath(startX, startY + 1); // move up
        estimatePath(startX, startY - 1); // move down
    }
}


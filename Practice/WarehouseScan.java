public class WarehouseScan {
    public static String warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return "total = 0, maxCoordinate = (0,0)";
        }

        int totalItems = 0;
        int maxItems = -1;
        int maxRow = 0;
        int maxCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                int currentItems = grid[r][c];
                totalItems += currentItems;

                if (currentItems > maxItems) {
                    maxItems = currentItems;
                    maxRow = r;
                    maxCol = c;
                }
            }
        }

        return "total = " + totalItems + ", maxCoordinate = (" + maxRow + ", " + maxCol + ")";
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };
        System.out.println(warehouseSummary(grid));
    }
}
package com.robisoft.bulbs.services.impls;

import com.robisoft.bulbs.services.BulbDistributionCalculator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SimpleBulbDistributionCalculator implements BulbDistributionCalculator {
    private final int WALL = 1;
    private final int CEIL = 0;
    private final int BULB = 2;

    private Map<Integer, List<Integer>> lightsInY;
    private Map<Integer, List<Integer>> lightsInX;

    public int[][] solve(int[][] matrix) {
        lightsInY = new HashMap<>();
        lightsInX = new HashMap<>();
        return solve(matrix, 0, 0);
    }

    private int[][] solve(int[][] matrix, int y, int x) {
        if (isOutsideRoom(matrix, y, x)) {
            return matrix;
        }

        if (canPutBulbInCell(matrix, y, x)) {
            if (doesntHaveLightFromAdjacent(matrix, y, x)) {
                matrix[y][x] = BULB;
                addToLightsMaps(y, x);
            } else {
                if (hasVerticalAndHorizontalNeighbors(matrix, y, x)) {
                    matrix[y][x] = BULB;
                    addToLightsMaps(y, x);

                    if (hasAnotherSourceOfLight(matrix, y, x)) {
                        List<String> visited = new ArrayList<>();
                        visited.add(0, getFormatedIndex(y, x));
                        visited = getSourcesOfLight(matrix, y, x, visited);

                        while (!visited.isEmpty()) {
                            int [] cardinal = explodeCardinal(visited.remove(0));
                            int lightY = cardinal[0];
                            int lightX = cardinal[1];

                            if (neighborsHaveAnotherSourceOfLight(matrix, lightY, lightX)) {
                                if (matrix[lightY][lightX] == BULB) {
                                    matrix[lightY][lightX] = CEIL;
                                    removeFromLightsMaps(lightY, lightX);
                                }
                            }
                        }
                    }
                }
            }
        }

        matrix = solve(matrix, y, x + 1);
        matrix = solve(matrix, y + 1, x);

        return matrix;
    }

    private boolean isOutsideRoom(int[][] matrix, int y, int x) {
        int yBorder = matrix.length - 1;

        if (y > yBorder) {
            return true;
        }

        int xBorder = matrix[y].length - 1;

        if (x > xBorder) {
            return true;
        }

        return false;
    }

    private boolean canPutBulbInCell(int[][] matrix, int y, int x) {
        return matrix[y][x] == CEIL;
    }

    private int[] explodeCardinal(String source) {
        String[] cardinal = source.split(",");
        int[] cardinalParsed = new int[2];

        cardinalParsed[0] = Integer.parseInt(cardinal[0]);
        cardinalParsed[1] = Integer.parseInt(cardinal[1]);

        return cardinalParsed;
    }

    private void addToLightsMaps(int y, int x) {
        List<Integer> adjacentInY = lightsInY.get(y);
        List<Integer> adjacentInX = lightsInX.get(x);

        if (adjacentInY == null) adjacentInY = new ArrayList<>();
        if (adjacentInX == null) adjacentInX = new ArrayList<>();
        adjacentInY.add(x);
        adjacentInX.add(y);

        lightsInY.put(y, adjacentInY);
        lightsInX.put(x, adjacentInX);
    }

    private void removeFromLightsMaps(int y, int x) {
        lightsInY.get(y).remove(lightsInY.get(y).indexOf(x));
        lightsInX.get(x).remove(lightsInX.get(x).indexOf(y));
    }

    private boolean neighborsHaveAnotherSourceOfLight(int[][] matrix, int y, int x) {
        // temporary remove this current node from lights.
        matrix[y][x] = CEIL;
        removeFromLightsMaps(y, x);

        for (int i = y; i >= 0; i--) {
            if (matrix[i][x] == WALL) {
                break;
            }
            if (matrix[i][x] != BULB && doesntHaveLightFromAdjacent(matrix, i, x)) {
                matrix[y][x] = BULB;
                addToLightsMaps(y, x);
                return false;
            }
        }

        for (int i = y; i < matrix.length; i++) {
            if (matrix[i][x] == WALL) {
                break;
            }
            if (matrix[i][x] != BULB && doesntHaveLightFromAdjacent(matrix, i, x)) {
                matrix[y][x] = BULB;
                addToLightsMaps(y, x);
                return false;
            }
        }

        for (int i = x; i >= 0; i--) {
            if (matrix[y][i] == WALL) {
                break;
            }
            if (matrix[y][i] != BULB && doesntHaveLightFromAdjacent(matrix, y, i)) {
                matrix[y][x] = BULB;
                addToLightsMaps(y, x);
                return false;
            }
        }

        for (int i = x; i < matrix[y].length; i++) {
            if (matrix[y][i] == WALL) {
                break;
            }
            if (matrix[y][i] != BULB && doesntHaveLightFromAdjacent(matrix, y, i)) {
                matrix[y][x] = BULB;
                addToLightsMaps(y, x);
                return false;
            }
        }

        matrix[y][x] = BULB;
        addToLightsMaps(y, x);

        return true;
    }

    private boolean hasVerticalAndHorizontalNeighbors(int[][] matrix, int y, int x) {
        return hasPathVertically(matrix, y, x) && hasPathHorizontally(matrix, y, x);
    }

    private boolean hasPathVertically(int [][] matrix, int y, int x) {
        int top = matrix.length - 1;
        int xLength = matrix[y].length - 1;
        int paths = 0;

        if (y < top && x < xLength && matrix[y + 1][x] != WALL) {
            paths++;
        }

        if (y > 0 && x < xLength && matrix[y - 1][x] != WALL) {
            paths++;
        }

        return paths > 0;
    }

    private boolean hasPathHorizontally(int [][] matrix, int y, int x) {
        int top = matrix[y].length - 1;
        int paths = 0;

        if (x < top && matrix[y][x + 1] != WALL) {
            paths++;
        }

        if (x > 0 && matrix[y][x - 1] != WALL) {
            paths++;
        }

        return paths > 0;
    }

    private List<String> getSourcesOfLight(int[][] matrix, int y, int x, List<String> visited) {
        List<Integer> adjacentInY = lightsInX.get(x);
        List<Integer> adjacentInX = lightsInY.get(y);

        if (adjacentInY != null && !adjacentInY.isEmpty()) {
            for (int i = y - 1; i >= 0; i--) {
                if (matrix[i][x] == WALL) {
                    break;
                }

                if (matrix[i][x] == BULB && !hasBeenVisited(i, x, visited)) {
                    visited.add(0, getFormatedIndex(i, x));
                    visited = getSourcesOfLight(matrix, i, x, visited);
                }
            }

            int length = matrix.length;
            for (int i = y + 1; i < length; i++) {
                if (matrix[i][x] == WALL) {
                    break;
                }

                if (matrix[i][x] == BULB && !hasBeenVisited(i, x, visited)) {
                    visited.add(0, getFormatedIndex(i, x));
                    visited = getSourcesOfLight(matrix, i, x, visited);
                }
            }
        }

        if (adjacentInX != null && !adjacentInX.isEmpty()) {
            for (int i = x - 1; i >= 0; i--) {
                if (matrix[y][i] == WALL) {
                    break;
                }

                if (matrix[y][i] == BULB && !hasBeenVisited(y, i, visited)) {
                    visited.add(0, getFormatedIndex(y, i));
                    visited = getSourcesOfLight(matrix, y, i, visited);
                }
            }

            int length = matrix[y].length;
            for (int i = x + 1; i < length; i++) {
                if (matrix[y][i] == WALL) {
                    break;
                }

                if (matrix[y][i] == BULB && !hasBeenVisited(y, i, visited)) {
                    visited.add(0, getFormatedIndex(y, i));
                    visited = getSourcesOfLight(matrix, y, i, visited);
                }
            }
        }

        return visited;
    }

    private boolean hasBeenVisited(int y, int x, List<String> visited) {
        if (visited.contains(getFormatedIndex(y, x))) {
            return true;
        }

        return false;
    }

    private String getFormatedIndex(int y, int x) {
        return String.format("%d,%d", y, x);
    }

    private boolean hasAnotherSourceOfLight(int[][] matrix, int y, int x) {
        return !doesntHaveLightFromAdjacent(matrix, y, x);
    }

    private boolean doesntHaveLightFromAdjacent(int[][] matrix, int y, int x) {
        List<Integer> adjacentInY = lightsInX.get(x);
        List<Integer> adjacentInX = lightsInY.get(y);

        if ((adjacentInX == null || adjacentInX.isEmpty()) && (adjacentInY == null || adjacentInY.isEmpty())) {
            return true;
        }

        if (lookForLightInY(matrix, y, x, adjacentInY)) {
            return false;
        }

        if (lookForLightInX(matrix, y, x, adjacentInX)) {
            return false;
        }

        return true;
    }

    private boolean lookForLightInY(int [][] matrix, int y, int x, List<Integer> adjacentInY) {
        if (adjacentInY == null || adjacentInY.size() == 0) {
            return false;
        }

        for (int i = y - 1; i >= 0; i--) {
            if (matrix[i][x] == WALL) {
                break;
            }

            if (matrix[i][x] == BULB) {
                return true;
            }
        }

        int length = matrix.length;
        for (int i = y + 1; i < length; i++) {
            if (matrix[i][x] == WALL) {
                break;
            }

            if (matrix[i][x] == BULB) {
                return true;
            }
        }

        return false;
    }

    private boolean lookForLightInX(int[][] matrix, int y, int x, List<Integer> adjacentInX) {
        if (adjacentInX == null || adjacentInX.size() == 0) {
            return false;
        }

        for (int i = x - 1; i >= 0; i--) {
            if (matrix[y][i] == WALL) {
                break;
            }

            if (matrix[y][i] == BULB) {
                return true;
            }
        }

        int length = matrix[y].length;
        for (int i = x + 1; i < length; i++) {
            if (matrix[y][i] == WALL) {
                break;
            }

            if (matrix[y][i] == BULB) {
                return true;
            }
        }

        return false;
    }
}

package matrixCalculator;

public class MatrixString {
    
    /**
     * This method equalizes the length of the element strings from the parameter vector array, then
     * returns a new array containing white space padded elements from the parameter array so that
     * the element strings are of equal length.
     * 
     * @param vectorArr             The input parameter vector array.
     * @return equalizedVectorArr   The new vector array with elements of equal length.
     */
    public static String[] equalizeElementWidth(String[] vectorArr) {
        String[] equalizedVectorArr = new String[vectorArr.length];
        //find length of longest element
        int greatestLength = 0;
        for (int i = 0; i < vectorArr.length; ++i) {
            if (vectorArr[i].length() > greatestLength) {
                greatestLength = vectorArr[i].length();
            }
        }
        //pad shorter elements with whitespace to equalize their length with longest element
        for (int i = 0; i < vectorArr.length; ++i) {
            String padLeft = "";
            String padRight = "";
            int difference = greatestLength - vectorArr[i].length();
            //even number of spaces to pad element
            if (difference % 2 ==0) {
                for (int j = 0; j < difference / 2; ++j) {
                    padLeft += " ";
                    padRight += " ";                    
                }
                equalizedVectorArr[i] = padLeft + vectorArr[i] + padRight;
            }
            //odd number of spaces to pad element puts one more padding space on right side
            else {
                for (int j = 0; j < difference / 2; ++j) {
                    padLeft += " ";
                    padRight += " ";                    
                }
                padRight += " ";
                equalizedVectorArr[i] = padLeft + vectorArr[i] + padRight;
            }
        }
        return equalizedVectorArr;

    }
    /**
     * This method equalizes the length of the element strings from the parameter matrix array, then
     * returns a new array containing white space padded elements from the parameter array so that 
     * the element strings are of equal length.
     * 
     * @param matrixArr             The input parameter matrix array.
     * @return equalizedMatrixArr   The new matrix array with elements of equal length.
     */
    public static String[][] equalizeElementWidth(String[][] matrixArr) {
        String[][] equalizedMatrixArr = new String[matrixArr.length][matrixArr[0].length];
        //find length of longest element
        int greatestLength = 0;
        for (int i = 0; i < matrixArr.length; ++i) {
            for (int j = 0; j < matrixArr[i].length; ++j) {
                if (matrixArr[i][j].length() > greatestLength) {
                    greatestLength = matrixArr[i][j].length();
                }
            }
        }
        //add whitespace padding to shorter elements to match longest element
        for (int i = 0; i < matrixArr.length; ++i) {
            int difference = 0;
            for (int j = 0; j < matrixArr[i].length; ++j) {
                String padLeft = "";
                String padRight = "";
                difference = greatestLength - matrixArr[i][j].length();
                //even number of spaces to pad element
                if (difference % 2 == 0) {
                    for (int k = 0; k < difference / 2; ++k) {
                        padLeft += " ";
                        padRight += " ";
                    }
                    equalizedMatrixArr[i][j] = padLeft + matrixArr[i][j] + padRight;
                }
                //odd number of spaces to pad element puts one more padding space on right side
                else {
                    for (int k = 0; k < difference / 2; ++k) {
                        padLeft += " ";
                        padRight += " ";
                    }
                    padRight += " ";
                    equalizedMatrixArr[i][j] = padLeft + matrixArr[i][j] + padRight;
                }
            }
        }
        return equalizedMatrixArr;
    }

    /**
     * This method returns a string equal to the sum of the lengths of a row of elements in a matrix 
     * after having padded each element of that row with a whitespace on either side. This 
     * facilitates correct spacing between the parenthesis of the matrix, as well as correct spacing 
     * between parenthesis and the augmentation border of augmented matrices.
     * 
     * @param matrixArr    The matrix whose width needs to be determined. Should have been equalized
     *                     prior to the calling of this method.
     * @return matrixWidth The string of length equal to the matrix's width.
     */
    public static String getMatrixWidth(String[][] matrixArr) {
        String matrixWidth = "";
        int elementWidth = 0;
        //sum the lengths of all elements
        for (int j = 0; j < matrixArr[0].length; ++j) {
            elementWidth += matrixArr[0][j].length();
            //each element padded with 2 spaces, one on either side
            matrixWidth += "  ";
        }
        //concatenate spaces, elementWidth times
        for (int i = 0; i < elementWidth; ++i) {
            matrixWidth += " ";
        }
        return matrixWidth;
    }
    /**
     * This method outputs a matrix from a two-dimensional array. 
     * 
     * - First the lengths of the strings in the matrix are made to be of equal length, with the 
     *   longest length element determining the length all elements will be.
     *   
     * - Then that length is multiplied by the number of columns in the matrix to determine the 
     *   total length of the elements in a row.
     * 
     * - Next, the number of spaces padding the elements of the rows are added to that length. This 
     *   determines the amount of space between elements of the matrix parenthesis that are not 
     *   populated with the matrix elements.
     *   
     * - Finally, a string constituting a visual representation of the matrix is generated and
     *   returned.
     * 
     * @param matrixArr          The two-dimensional array.
     * @return matrixPrintString The visual representation of the matrix in the form of a string.
     */
    public static String printMatrix(String[][] matrixArr) {
        //call equalizeElementWidth to create matrix with element strings of equal length
        String[][] equalMatrixArr = equalizeElementWidth(matrixArr);
        //get width of matrix to build rows without elements
        String matrixWidth = getMatrixWidth(equalMatrixArr);
        //generate a string representation of the matrix
        String matrixPrintString = "/" + matrixWidth + "\\\n";        
        for (int i = 0; i < equalMatrixArr.length; ++i) {
            matrixPrintString += "|";
            for (int j = 0; j < equalMatrixArr[i].length; ++j) {
                matrixPrintString += " " + equalMatrixArr[i][j] + " ";
            }
            matrixPrintString += "|\n";
            if (i < equalMatrixArr.length - 1) {
                matrixPrintString += "|" + matrixWidth + "|\n";
            }
            else {
                matrixPrintString += "\\" + matrixWidth + "/\n";
            }
        }
        return matrixPrintString;
    }
    
    /**
     * This method returns a string that visually represents an augmented matrix consisting of a
     * a matrix and a vector.
     * 
     * @param matrixArr          The matrix on the left side of the augmented matrix.
     * @param augVectorArr       The vector on the right side of the augmented matrix.
     * @return matrixPrintString The string that visually represents the augmented matrix.
     */
    public static String printAugmentedMatrix(String[][] matrixArr, String[] augVectorArr) {
        //ensure matrix and vector are of compatible sizes
        if (matrixArr.length != augVectorArr.length) {
            System.out.println("The number of rows do not match");
            return null;
        }
        //call equalizeElementWidth to create arrays with element strings of equal length
        String[][] equalMatrixArr = equalizeElementWidth(matrixArr);
        String[] equalVectorArr = equalizeElementWidth(augVectorArr);
        //get widths of matrix and vector to build rows without elements
        String leftMatrixWidth = getMatrixWidth(equalMatrixArr);
        String rightVectorWidth = "  ";//two padding spaces, one on each side of the element
        for (int i = 0; i < equalVectorArr[0].length(); ++i) {
            rightVectorWidth += " ";
        }
        String augMatrixWidth = leftMatrixWidth + "|" + rightVectorWidth;
        //generate a string representation of the augmented matrix
        String matrixPrintString = "/" + augMatrixWidth + "\\\n";        
        for (int i = 0; i < equalMatrixArr.length; ++i) {
            matrixPrintString += "|";
            for (int j = 0; j < equalMatrixArr[i].length; ++j) {
                matrixPrintString += " " + equalMatrixArr[i][j] + " ";
            }
            matrixPrintString += "| " + equalVectorArr[i] + " |\n";
            if (i < equalMatrixArr.length - 1) {
                matrixPrintString += "|" + augMatrixWidth + "|\n";
            }
            else {
                matrixPrintString += "\\" + augMatrixWidth + "/\n";
            }
        }
        return matrixPrintString;
    }
    
    /**
     * This method returns a string that visually represents an augmented matrix consisting of two
     * matrices.
     * 
     * @param matrixArr          The matrix on the left side of the augmented matrix.
     * @param augMatrixArr       The matrix on the right side of the augmented matrix.
     * @return matrixPrintString The string that visually represents the augmented matrix.
     */
    public static String printAugmentedMatrix(String[][] matrixArr, String[][] augMatrixArr) {
        //ensure matrices are of compatible sizes
        if (matrixArr.length != augMatrixArr.length) {
            System.out.println("Number of rows must be the same for both matrices");
            return null;
        }
        //call equalizeElementWidth to create arrays with element strings of equal length
        String[][] equalMatrixArr = equalizeElementWidth(matrixArr);
        String[][] equalAugMatrix = equalizeElementWidth(augMatrixArr);
        //get widths of matrices to build rows without elements
        String leftMatrixWidth = getMatrixWidth(equalMatrixArr);
        String rightMatrixWidth = getMatrixWidth(equalAugMatrix);
        String augMatrixWidth = leftMatrixWidth + "|" + rightMatrixWidth;
        //generate a string representation of the augmented matrix
        String matrixPrintString = "/" + augMatrixWidth + "\\\n";        
        for (int i = 0; i < equalMatrixArr.length; ++i) {
            matrixPrintString += "|";
            for (int j = 0; j < equalMatrixArr[i].length; ++j) {
                matrixPrintString += " " + equalMatrixArr[i][j] + " ";
            }
            matrixPrintString += "|";
            for (int j = 0; j < equalAugMatrix[i].length; ++j) {
                matrixPrintString += " " + equalAugMatrix[i][j] + " ";
            }
            matrixPrintString += "|\n";
            if (i < equalMatrixArr.length - 1) {
                matrixPrintString += "|" + augMatrixWidth + "|\n";
            }
            else {
                matrixPrintString += "\\" + augMatrixWidth + "/\n";
            }
        }
        return matrixPrintString;
    }      
}
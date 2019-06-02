import java.awt.*;
import java.io.*;

public class Engine {

    // Input and output files
    private static File inputFile;
    private static File outputFile;

    // Avg fleet util
    private static int fhPerDay;
    private static int fcPerDay;

    // Other data
    private static Point currentUtil = new Point();
    private static Point forecastedUtil = new Point();
    private static Point limStart = new Point();
    private static Point limEnd = new Point();

    // Max CYC for FLS
    public static final int FLS_FIG1_CYC = 75000;
    public static final int FLS_FIG2_CYC = 56000;

    public String process() {
        // Set output file to this directory
        outputFile = new File(inputFile.getParentFile()+"\\FLS Output.csv");

        StringBuilder sb = new StringBuilder();
        int i=0;

        // Read from file
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));

            String line = null;

            // Read line by line and process data
            while((line = br.readLine()) != null) {
                sb.append(line);
                i++;

                // If header
                if(i==1) {
                    sb.append(",Fig 1 FH,Fig 1 FC, Fig 2 FH, Fig 2 FC\n");
                    continue;
                }

                // Split line
                String[] lineArr = line.split(",");
                int currentFH = Integer.parseInt(lineArr[1]);
                int currentFC = Integer.parseInt(lineArr[2]);

                int forecastFH = 0;

                // Set current utilization
                // lineArr[1] = FH, lineArr[2] = FC
                currentUtil.setLocation(currentFH, currentFC);

                // Set forecasted utilization for FLS Fig1 (75k CYC)
                forecastFH = (int) ((((FLS_FIG1_CYC-currentFC)/fcPerDay)*fhPerDay)+currentFH);
                forecastedUtil.setLocation(forecastFH, FLS_FIG1_CYC);
                limStart.setLocation(45000,75000);
                limEnd.setLocation(100000,30000);
                Point fig1Result = calculateIntersection();

                // Set forecasted utilization for FLS Fig2 (56k CYC)
                forecastFH = (int) ((((FLS_FIG2_CYC-currentFC)/fcPerDay)*fhPerDay)+currentFH);
                forecastedUtil.setLocation(forecastFH, FLS_FIG2_CYC);
                limStart.setLocation(33000,56000);
                limEnd.setLocation(75000,22000);
                Point fig2Result = calculateIntersection();

                // Process result
                sb.append(","+fig1Result.getX()+","+fig1Result.getY()+","+fig2Result.getX()+","+fig2Result.getY()+"\n");
            }

            br.close();

            // Write result to file
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
            bw.write(String.valueOf(sb));
            bw.close();

        } catch (Exception e) {
            return "Error occured. Make sure of the following:\n- Input/output files are not open.\n- Input file is in correct format.";
        }

        return "FLS calculated for "+(i-1)+" aircraft.\nFile saved at: "+outputFile;
    }

    public Point calculateIntersection() {
        // First line (util)
        double x1 = currentUtil.getX();
        double y1 = currentUtil.getY();
        double x2 = forecastedUtil.getX();
        double y2 = forecastedUtil.getY();

        // Second line (limit)
        double x3 = limStart.getX();
        double y3 = limStart.getY();
        double x4 = limEnd.getX();
        double y4 = limEnd.getY();

        double d = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4);
        if (d == 0) return null;
        double x = ((x3-x4)*(x1*y2-y1*x2)-(x1-x2)*(x3*y4-y3*x4))/d;
        double y = ((y3-y4)*(x1*y2-y1*x2)-(y1-y2)*(x3*y4-y3*x4))/d;

        // Cast to int
        int xi = (int) x;
        int yi = (int) y;

        return new Point(xi, yi);
    }

    public static File getInputFile() {
        return inputFile;
    }

    public static void setInputFile(File inputFile) {
        Engine.inputFile = inputFile;
    }

    public static File getOutputFile() {
        return outputFile;
    }

    public static void setOutputFile(File outputFile) {
        Engine.outputFile = outputFile;
    }

    public static int getFhPerDay() {
        return fhPerDay;
    }

    public static void setFhPerDay(int fhPerDay) {
        Engine.fhPerDay = fhPerDay;
    }

    public static int getFcPerDay() {
        return fcPerDay;
    }

    public static void setFcPerDay(int fcPerDay) {
        Engine.fcPerDay = fcPerDay;
    }

    public static Point getCurrentUtil() {
        return currentUtil;
    }

    public static void setCurrentUtil(Point currentUtil) {
        Engine.currentUtil = currentUtil;
    }

    public static Point getForecastedUtil() {
        return forecastedUtil;
    }

    public static void setForecastedUtil(Point forecastedUtil) {
        Engine.forecastedUtil = forecastedUtil;
    }

    public static Point getLimStart() {
        return limStart;
    }

    public static void setLimStart(Point limStart) {
        Engine.limStart = limStart;
    }

    public static Point getLimEnd() {
        return limEnd;
    }

    public static void setLimEnd(Point limEnd) {
        Engine.limEnd = limEnd;
    }
}
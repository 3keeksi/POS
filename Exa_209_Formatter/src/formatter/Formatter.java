/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formatter;

/**
 *
 * @author Denis Imeri
 */
public class Formatter {

    public String join(String delim, String... values) {
        String result = "", prefix = "";
        for (String s : values) {
            result += prefix + s;
            prefix = delim;
        }
        return result;
    }

    public String join(String delim, int... values) {
        if (values == null) {
            return "keine Parameter";
        }
        String result = "", prefix = "";
        for (int i = 0; i < values.length; i++) {
            result += prefix + values[i];
            prefix = delim;
        }
        return result;
    }

    public String join(String delim, double... values) {
        String result = "", prefix = "";
        for (int i = 0; i < values.length; i++) {
            result += prefix + String.format("%.2f", values[i]);
            prefix = delim;
        }
        return result;
    }

    public String time(String fmt, int... values) {
        if (fmt == "hh:mm:ss" || fmt == "HH:mm:ss" || fmt == "hh:mm" || fmt == "HH:mm") {
            if (values != null) {
                int leng = values.length;
                if (leng < 1 || (0 > values[0] && values[0] > 24)) {
                    return "Hours should be in the range from 0 to 23";
                }
                if (leng < 2 || 0 > values[1] && values[1] > 59) {
                    return "Minutes should be in the range from 0 to 59 or you forgot a parameter";
                }
                if (leng < 3 || (0 < values[2] && values[2] > 59)) {
                    return "Seconds should be in the range from 0 to 59 or you forgot a parameter";
                }
                if (fmt.startsWith("HH")) {
                    if (fmt.contains("mm:ss")) {
                        String after = "";
                        if (values[0] == 24) {
                            after = "AM";
                        }
                        if (11 < values[0] && values[0] < 24) {
                            values[0] -= 12;
                            after="PM";
                        }
                        if (values[0] == 12) {
                            after = "PM";
                            values[0] += 12;
                        }
                        if (values[0] < 12) {
                            values[0] += 24;
                            after = "AM";
                        }
                        String txt = String.format("%2d:%2d:%2d %s", values[0] - 12, values[1], values[2], after);

                        return txt;
                    }
                    String after = "";
                    if (values[0] == 24) {
                        after = "AM";
                    }
                    if (values[0] < 24) {
                        values[0] -= 12;
                    }
                    if (values[0] == 12) {
                        after = "PM";
                        values[0] += 12;
                    }
                    if (values[0] < 12) {
                        values[0] += 24;
                        after = "AM";
                    }
                    String txt = String.format("%2d:%2d %s", values[0] - 12, values[1], after);

                    return txt;
                } 
                if(fmt.startsWith("hh")) {
                    if (fmt.contains("mm:ss")) {
                        String txt = String.format("%2d:%2d:%2d", values[0], values[1], values[2]);

                        return txt;
                    }
                    String txt = String.format("%2d:%2d", values[0], values[1]);

                    return txt;
                }
            }

        } else {
            return "wrong format\n"
                    + "Options:\n"
                    + "HH:mm:ss = AM & PM with seconds\n"
                    + "HH:mm = AM & PM without seconds\n"
                    + "hh:mm:ss = AM & PM with seconds\n"
                    + "h:mm = AM & PM without seconds\n";
        }
        return "";
    }
}

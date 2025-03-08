package Easy;

public class RobotReturnOrigin {
    public boolean judgeCircle(String moves) {
        int[] move = new int[4];
        for (int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == 'U') {
                move[0] += 1;
            } else if (moves.charAt(i) == 'D') {
                move[1] += 1;
            } else if (moves.charAt(i) == 'L') {
                move[2] += 1;
            } else {
                move[3] += 1;
            }
        }
        if (move[0] == move[1] && move[2] == move[3]) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        RobotReturnOrigin test = new RobotReturnOrigin();
        System.out.println(test.judgeCircle("UDUD"));
        System.out.println(test.judgeCircle("UDUD"));
    }
}
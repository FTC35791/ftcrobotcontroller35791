import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class Paths {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(82.2975, 9.8095, 90);
    private final Pose tolaunch = poseFactory.of(107.6272, 22.7807, 135);
    private final Pose tolaunchControl1 = poseFactory.of(98.8124, 6.0315, 0);
    private final Pose point2 = poseFactory.of(125.7959, 20.6838, -6.5835);

    public Path tolaunch() {
        return Paths.curve(start, tolaunchControl1, tolaunch).linear(start, tolaunch);
    }

    public Path path2() {
        return Paths.line(tolaunch, point2).tangent();
    }
}

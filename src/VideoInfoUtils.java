import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/4/30 10:17
 * Modified:
 * Description:
 */
public class VideoInfoUtils {

    public static void getVideoInfoByXuggler(String fileName) {
        IContainer container = IContainer.make();
        int result = container.open(fileName, IContainer.Type.READ, null);
        if (result < 0) {
            throw new RuntimeException("Failed to open video file[" + fileName + "]");
        }

        for (int i = 0; i < container.getNumStreams(); i++) {
            IStream iStream = container.getStream(i);
            IStreamCoder iStreamCoder = iStream.getStreamCoder();
            ICodec iCodec = iStreamCoder.getCodec();
            switch (iCodec.getType()) {
                case CODEC_TYPE_VIDEO:
                    System.out.println("视频宽高：" + iStreamCoder.getWidth() + "x" + iStreamCoder.getHeight());
                    System.out.println("视频时长：" + (int) Math.ceil(container.getDuration() / 1000));
                    System.out.println("视频流编码：" + iCodec.getName());
                    break;
                case CODEC_TYPE_AUDIO:
                    System.out.println("音频流编码：" + iCodec.getName());
                    break;
            }
        }
    }

    /**
     * 视频宽高：1920x1080
     * 视频时长：90439
     * 视频流编码：h264
     * 音频流编码：mp2
     * 视频宽高：1920x1080
     * 视频时长：31800
     * 视频流编码：h264
     * 音频流编码：aac
     * 视频宽高：1280x720
     * 视频时长：3119549
     * 视频流编码：h264
     * 音频流编码：aac
     */
}

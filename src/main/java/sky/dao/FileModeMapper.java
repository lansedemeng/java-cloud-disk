package sky.dao;

import sky.pojo.FileMode;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: krny
 * Date: 2017/2/24 0024
 * Time: 12:08
 * To change this template use FileMode | Settings | FileMode Templates.
 */
public interface FileModeMapper {
    List<FileMode> queryAll(FileMode fileMode);

    List<FileMode> queryByType(FileMode fileMode);

    int insert(FileMode fileMode);
}
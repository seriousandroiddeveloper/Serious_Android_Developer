package in.ultraneo.seriousandroiddeveloper;



import java.io.File;


abstract class AlbumStorageDirFactory {
	public abstract File getAlbumStorageDir(String albumName);
}

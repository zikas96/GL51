class Picture {
    File file
}


class UploadedPicture {
    String pictureUrl
    String thumbnailUrl
}


interface PictureResizer {
    Picture resize(Picture picture, Integer size)
}

@Singleton
class PictureResizerImpl implements PictureResizer {
    Picture resize(Picture picture, Integer size) {
        
    }
}


interface WatermarkApplyer {
    Picture apply(Picture picture, Picture watermark)
}

@Singleton
class WatermarkApplyerImpl implements WatermarkApplyer {
    
    Picture apply(Picture picture, Picture watermark) {
        
        def pictureWithWaterMark = // TODO : Apply the watermark on the picture
        
        return pictureWithWaterMark
    }
}


interface PictureSaver {
    UploadedPicture save(Picture picture, Picture thumbnail)
}

@Singleton
class CloudPictureSaver implements PictureSaver {
    UploadedPicture save(Picture picture, Picture thumbnail) {
    }
}


interface PictureDatabaseHandler {
    Void storePicture(UploadedPicture uploadedPicture)
}

@Singleton
class PictureDatabaseHandlerImpl implements PictureDatabaseHandler {
    Void storePicture(UploadedPicture uploadedPicture) {
    }
}

// Main program
class PictureStorageHandler {
    
    final static Integer UPLOADED_PICTURE_SIZE = 1024
    
    final static Integer THUMBNAIL_SIZE = 50
    
    final static Picture THUMBNAIL_WATERMARK = // TODO : Initialize the watermark image
    
    @Inject
    PictureResizer pictureResizer
    
	@Inject
	WatermarkApplyer watermarkApplyer
	
	@Inject
	PictureSaver pictureSaver
	
	@Inject
	PictureDatabaseHandler databaseHandler
	
    void upload(Picture picture) {
        
        def resizedPicture = pictureResizer.resize(picture, UPLOADED_PICTURE_SIZE)
        
		def thumbnail = pictureResizer.resize(picture, THUMBNAIL_SIZE)
        thumbnail = watermarkApplyer.apply(thumbnail, THUMBNAIL_WATERMARK)
		
		def uploadedPicture = pictureSaver.save(resizedPicture, thumbnail)
		
		databaseHandler.storePicture(uploadedPicture)
    }
}

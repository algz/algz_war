package com.cf611.fmuFileManager;

import com.cf611.util.ProTablePage;

public interface FMUFileService {

	ProTablePage<FMUFile> getFMUFiles(ProTablePage<FMUFile> pageParam, FMUFile param);

}

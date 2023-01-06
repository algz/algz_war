package com.cf611.requirmentDataBase.fmuFileBase;

import com.cf611.util.ProTablePage;

public interface FMUFileService {

	ProTablePage<FMUFile> getFMUFiles(ProTablePage<FMUFile> pageParam, FMUFile param);

}

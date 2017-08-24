package com.archsystemsinc.ipms.mpxj.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import net.sf.mpxj.MPXJException;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.Resource;
import net.sf.mpxj.mpp.MPPReader;
import net.sf.mpxj.reader.ProjectReader;

public class MSProjectFileReader {
	// util
	public boolean readMPPFile(final File mppInputFile, final File resultFile) {
		boolean fileReadResult = false;

		try {

			final ProjectReader projectReader = new MPPReader();
			final ProjectFile projectFile = projectReader.read(mppInputFile);
			final BufferedWriter out = new BufferedWriter(new FileWriter(
					resultFile));
			for (final Resource resource : projectFile
					.getAllResources()) {
				out.write("Resource: " + resource.getName() + " (Unique ID="
						+ resource.getUniqueID() + ")");
			}
			out.close();

		} catch (final MPXJException e) {
			fileReadResult = false;
		} catch (final FileNotFoundException e) {
			fileReadResult = false;
		} catch (final IOException e) {
			fileReadResult = false;
		}
		fileReadResult = true;
		return fileReadResult;

	}

}

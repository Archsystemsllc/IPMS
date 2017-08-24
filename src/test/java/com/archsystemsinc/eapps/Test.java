package com.archsystemsinc.eapps;


import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.mpp.MPPReader;
public class Test {

	public static void main(String[]args) throws Exception {
		final MPPReader reader = new MPPReader();
		//ProjectReader reader = ProjectReaderUtility.getProjectReader("/Users/leksrej/Downloads/EPM_PCMG.mpp");
		final ProjectFile projectFile = reader.read("/Users/leksrej/Downloads/EPM_PCMG.mpp");
		System.out.println("XXXXXXXXXXX");
	}
}

package com.laratecsys.neogrid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.laratecsys.neogrid.entities.AssemblyLine;
import com.laratecsys.neogrid.entities.ProductionProcess;
import com.laratecsys.neogrid.utils.ExtractMinute;

public class App {
	public static void main(String[] args) throws FileNotFoundException, IOException {

		final Options options = createOptions();
		final CommandLine line = getCommandLine(options, args);
		final List<AssemblyLine> assemblyLines = new ArrayList<AssemblyLine>();
		final AssemblyLine assemblyLine1 = new AssemblyLine(1);
		final AssemblyLine assemblyLine2 = new AssemblyLine(2);
		Date initTime = new Date();
		initTime.setHours(9);
		initTime.setMinutes(0);
		initTime.setSeconds(0);
		GregorianCalendar nextActivity = new GregorianCalendar();
		nextActivity.setTime(initTime);
		SimpleDateFormat formatPrintHour = new SimpleDateFormat("HH:mm");

		File file = new File(line.getOptionValue("file"));

		final BufferedReader stream = new BufferedReader(new FileReader(file));

		List<ProductionProcess> assemblyActivities = new ArrayList<ProductionProcess>();
		for (String fileLine = stream.readLine(); fileLine != null; fileLine = stream.readLine()) {

			ProductionProcess assemblyActivitie = new ProductionProcess();

			assemblyActivitie.setActivityTime(nextActivity.getTime());
			assemblyActivitie.setActivityTitle(fileLine);

			assemblyActivities.add(assemblyActivitie);

			nextActivity.setTime(assemblyActivitie.getActivityTime());
			nextActivity.add(Calendar.MINUTE, ExtractMinute.minute(fileLine));
			System.out.println(formatPrintHour.format(assemblyActivitie.getActivityTime()) + " "
					+ assemblyActivitie.getActivityTitle());
		}
		assemblyLine1.setAssemblyActivities(assemblyActivities);

		stream.close();

	}

	private static CommandLine getCommandLine(final Options options, final String[] args) {
		@SuppressWarnings("deprecation")
		final CommandLineParser parse = new GnuParser();
		CommandLine line = null;
		try {
			line = parse.parse(options, args, false);
		} catch (ParseException e) {
			final HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("Parâmetros de entrada", options);
		}

		return line;
	}

	private static Options createOptions() {

		final Options options = new Options();

		options.addOption("file", true, "caminho do arquivo de input");

		return options;
	}
}

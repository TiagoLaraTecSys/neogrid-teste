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
import com.laratecsys.neogrid.utils.CheckHour;
import com.laratecsys.neogrid.utils.ExtractMinute;

public class App extends CheckHour{
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Integer i = 1;
		final Options options = createOptions();
		final CommandLine line = getCommandLine(options, args);
		Date initTime = new Date();
		initTime.setHours(9);
		initTime.setMinutes(0);
		initTime.setSeconds(0);
		GregorianCalendar nextActivity = new GregorianCalendar();
		nextActivity.setTime(initTime);
		SimpleDateFormat formatPrintHour = new SimpleDateFormat("HH:mm");

		File file = new File(line.getOptionValue("file"));

		final BufferedReader stream = new BufferedReader(new FileReader(file));

		System.out.printf("Linha de montagem %d: \n", i);
		for (String fileLine = stream.readLine(); fileLine != null; fileLine = stream.readLine()) {

			ProductionProcess assemblyActivitie = new ProductionProcess();
			GregorianCalendar nextTime = new GregorianCalendar();
			nextTime.setTime(nextActivity.getTime());
			nextTime.add(Calendar.MINUTE, ExtractMinute.minute(fileLine));
			
			if (isLaunchTime(nextTime.getTime())) {
				Date almoco = new Date();
                almoco.setHours(13);
                almoco.setMinutes(0);
				nextActivity.setTime(almoco);
				System.out.println("12:00 Horário de Almoço");
			}
			
			if(isLaborTime(nextActivity.getTime()) && ExtractMinute.minute(fileLine) >=30) {
				System.out.println(formatPrintHour.format(nextActivity.getTime()) +" Ginástica Laboral\n");
				initTime.setHours(9);
				initTime.setMinutes(0);
				nextActivity.setTime(initTime);
				i++;
				
				System.out.printf("Linha de montagem %d: \n", i);
			}
			assemblyActivitie.setActivityTime(nextActivity.getTime());
			assemblyActivitie.setActivityTitle(fileLine);

			nextActivity.setTime(assemblyActivitie.getActivityTime());
			
			nextActivity.add(Calendar.MINUTE, ExtractMinute.minute(fileLine));
			
			System.out.println(formatPrintHour.format(assemblyActivitie.getActivityTime()) + " " + assemblyActivitie.getActivityTitle());
		}
		
		System.out.println(formatPrintHour.format(nextActivity.getTime()) +" Ginástica Laboral\n");

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

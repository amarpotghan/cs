package app.cs.controller;

import java.security.KeyStore.CallbackHandlerProtection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.cs.interfaces.IService;

/**
 * The Class ChapterController. TODO. com.cs.business.ifacadeservices controller
 * -> common facade ->>>(|) ->i***interface call ichapter ->Impl idimension
 * ->Impl
 */
@Controller
public class ChapterController {

	/** The Constant CREATE. */
	private static final String CREATECHAPTER = "/chapter/create/{type}/name/{name}/path/{path}/folder/{folder}";
	private static final String CREATEPAGE = "/page/create/{type}/name/{name}/path/{path}/folder/{folder}";
	private static final String MOVECHAPTER = "/chapter/move/{type}/name/{name}/path/{path}/folder/{folder}/newpath/{newPath}";
	private static final String MOVEPAGE = "/page/move/{type}/name/{name}/path/{path}/folder/{folder}/newpath/{newPath}";

	/** The chapter service. */
	private IService chapterService;

	/**
	 * Instantiates a new chapter controller.
	 * 
	 * @param chapterService
	 *            the chapter service
	 * @param factory
	 *            the factory
	 */
	@Autowired
	public ChapterController(IService chapterService) {
		this.chapterService = chapterService;

	}

	/**
	 * Creates the.
	 * 
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param path
	 *            the path
	 * @param isFolder
	 *            the is folder
	 * @return the string
	 */
	@RequestMapping(value = { CREATECHAPTER, CREATEPAGE })
	public @ResponseBody
	String create(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("path") String path,
			@PathVariable("folder") String isFolder) {

		return chapterService.create(type, name, path, isFolder);

	}

	/**
	 * Move.
	 * 
	 * @param type
	 *            the type
	 * @param name
	 *            the name
	 * @param path
	 *            the path
	 * @param isFolder
	 *            the is folder
	 * @param newPath
	 *            the new path
	 */
	@RequestMapping(value = { MOVECHAPTER, MOVEPAGE })
	public @ResponseBody
	String move(@PathVariable("type") String type,
			@PathVariable("name") String name,
			@PathVariable("path") String path,
			@PathVariable("folder") String isFolder,
			@PathVariable("newpath") String newPath) {

		System.out.println("==>" + type + name + path + isFolder + newPath);
		chapterService.move(type, name, path, isFolder, newPath);
		return name;

	}
}

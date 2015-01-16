    window.onload = function () {
				var editor;
				var currentlanguage = 'ru';

				if (com.wiris.jsEditor.defaultBasePath) {
					editor = com.wiris.jsEditor.JsEditor.newInstance({
						'language': 'ru'
					});
				}
				else {
					editor = new com.wiris.jsEditor.JsEditor('../resources');
				}

				editor.insertInto(document.getElementById('editorContainer'));



				/*document.getElementById('getMathML').onclick = function () {
					document.getElementById('mathML').value = editor.getMathML();
				}

				document.getElementById('setMathML').onclick = function () {
					editor.setMathML(document.getElementById('mathML').value);
				}

				document.getElementById('getLaTeX').onclick = function () {
					document.getElementById('mathML').value = getLaTeX(editor.getMathML(), function () {});
				}

				document.getElementById('setLaTeX').onclick = function () {
					editor.setMathML(getMathML(document.getElementById('mathML').value));
				}*/

				if (document.getElementById('openImage') != null) {
					document.getElementById('openImage').onclick = function () {
						openResource(document.getElementById('imageURL').value, editor.getMathML());
					}
				}

				if (document.getElementById('openFlash') != null) {
					document.getElementById('openFlash').onclick = function () {
						openResource(document.getElementById('flashURL').value, editor.getMathML());
					}
				}

				setMmlLocal = function() {
					if (editor.isReady()) {
						var mml;
						mml = getParameter("mml","");
						if (mml!="")
							editor.setMathML(mml);
					} else {
						setTimeout(setMmlLocal,200);
					}
				}

				setTimeout(setMmlLocal,200);
				setLanguage();

				source = 'mathml';
				lastSource = null;
				var waitingForSetSource = false;
				var counter = 0;

				setInterval(function () {
					var mathML = editor.getMathML();

					if (mathML != lastSource) {
						++counter;
						var current = counter;

						if (document.getElementById('page-accessibility') != null) {
							if (currentlanguage == 'ca') {
								document.getElementById('accessibleFrameCatalan').style.display = 'block';
								document.getElementById('accessibleCatalan').style.display = 'block';
								getAccessible(mathML, function (content) {
									if (current != counter) {
										return;
									}

									if (document.getElementById('accessibleContainerCatalan') != null) {
										document.getElementById('accessibleContainerCatalan').textContent = content;

										if (content.trim().length == 0) {
											document.getElementById('accessibleEmptyTextCatalanh').style.display = 'block';
											document.getElementById('accessibleContainerCatalan').style.display = 'none';
										}
										else {
											document.getElementById('accessibleEmptyTextCatalan').style.display = 'none';
											document.getElementById('accessibleContainerCatalan').style.display = 'block';
										}
									}
								}, 'ca');
							}

							if (currentlanguage == 'ar' || currentlanguage == 'ar_sa') {
								document.getElementById('accessibleFrameArabic').style.display = 'block';
								document.getElementById('accessibleArabic').style.display = 'block';
								getAccessible(mathML, function (content) {
									if (current != counter) {
										return;
									}

									if (document.getElementById('accessibleContainerArabic') != null) {
										document.getElementById('accessibleContainerArabic').textContent = content;

										if (content.trim().length == 0) {
											document.getElementById('accessibleEmptyTextArabic').style.display = 'block';
											document.getElementById('accessibleContainerArabic').style.display = 'none';
										}
										else {
											document.getElementById('accessibleEmptyTextArabic').style.display = 'none';
											document.getElementById('accessibleContainerArabic').style.display = 'block';
										}
									}
								}, 'ar');
							}

							getAccessible(mathML, function (content) {
								if (current != counter) {
									return;
								}

								if (document.getElementById('accessibleContainerEnglish') != null) {
									document.getElementById('accessibleContainerEnglish').textContent = content;

									if (content.trim().length == 0) {
										document.getElementById('accessibleEmptyTextEnglish').style.display = 'block';
										document.getElementById('accessibleContainerEnglish').style.display = 'none';
									}
									else {
										document.getElementById('accessibleEmptyTextEnglish').style.display = 'none';
										document.getElementById('accessibleContainerEnglish').style.display = 'block';
									}
								}
							}, 'en');

							getAccessible(mathML, function (content) {
								if (current != counter) {
									return;
								}

								if (document.getElementById('accessibleContainerSpanish') != null) {
									document.getElementById('accessibleContainerSpanish').textContent = content;

									if (content.trim().length == 0) {
										document.getElementById('accessibleEmptyTextSpanish').style.display = 'block';
										document.getElementById('accessibleContainerSpanish').style.display = 'none';
									}
									else {
										document.getElementById('accessibleEmptyTextSpanish').style.display = 'none';
										document.getElementById('accessibleContainerSpanish').style.display = 'block';
									}
								}
							}, 'es');
						}

						if (document.getElementById('source') != null) {
							if (source == 'mathml' && !waitingForSetSource) {
								document.getElementById('source').value = mathML;
								document.getElementById('source').style.color = null;
								document.getElementById('source_set').style.display = null;
							}
							else if (source == 'latex') {
								getLaTeX(mathML, function (content) {
									if (current != counter) {
										return;
									}
									if (!waitingForSetSource) {
										document.getElementById('source').value = content;
										document.getElementById('source').style.color = null;
										document.getElementById('source_set').style.display = null;
									}
								});
							}
						}

						if (document.getElementById('page-mathml-latex') != null) {
							document.getElementById('source-mathml').value = mathML;
							getLaTeX(mathML, function (content) {
								document.getElementById('source-latex').value = content;
							});
						}


						if (document.getElementById('page-main') != null) {
							document.getElementById('dynamicimg').src='http://www.wiris.net/demo/editor/render.png?demo-image&mml='+wrs_urlencode(wrs_mathmlEntities(mathML));
							//document.getElementById('dynamicjs').innerHTML = mathML;
							document.getElementById('dynamicimg').onload = function() {
								//document.getElementById('dynamicflash').data = 'http://www.wiris.net/demo/editor/render.swf?demo-image&mml='+wrs_urlencode(wrs_mathmlEntities(mathML));
								//document.getElementById('dynamicflash').height = document.getElementById('dynamicimg').height;
								//document.getElementById('dynamicflash').width = document.getElementById('dynamicimg').width;
								document.getElementById('dynamicflashbox').innerHTML = '<object id="dynamicflash" type="application/x-shockwave-flash" data=\'http://www.wiris.net/demo/editor/render.swf?demo-image&mml='+wrs_urlencode(wrs_mathmlEntities(mathML))+'\' width="'+document.getElementById('dynamicimg').width+'" height="'+document.getElementById('dynamicimg').height+'"><param name="loop" value="true" /><param name="menu" value="false" /><param name="wmode" value="transparent" /><p>Flash is disabled!</p></object>';
							}
							//com.wiris.jsEditor.JsViewerMain.main();
						}

						lastSource = mathML;
					}
				}, 1000);


				/*window.changeSourceTo = function (newSource) {
					lastSource = null;
					source = newSource;
					document.getElementById('source').value = '';
					document.getElementById('source').style.color = null;
					document.getElementById('source_set').style.display = null;
				}

				if (document.getElementById('source') != null) {
					document.getElementById('source').oninput = document.getElementById('source').onkeypress = function () {
						waitingForSetMathML = true;
						document.getElementById('source').style.color = '#000';
						document.getElementById('source_set').style.display = 'block';
					}
				}

				if (document.getElementById('source_set_button') != null) {
					document.getElementById('source_set_button').onclick = function () {
						if (source == 'mathml') {
							editor.setMathML(document.getElementById('source').value);
						}
						else if (source == 'latex') {
							editor.setMathML(getMathML(document.getElementById('source').value));
						}

						document.getElementById('source').style.color = null;
						document.getElementById('source_set').style.display = null;
						waitingForSetSource = false;
					}
				}

				if (document.getElementById('source_cancel_button') != null) {
					document.getElementById('source_cancel_button').onclick = function () {
						if (source == 'mathml') {
							document.getElementById('source').value = editor.getMathML();
							document.getElementById('source').style.color = null;
						}
						else if (source == 'latex') {
							getLaTeX(editor.getMathML(), function (content) {
								document.getElementById('source').value = content;
								document.getElementById('source').style.color = null;
							});
						}

						document.getElementById('source_set').style.display = null;
						waitingForSetSource = false;
					}
				}*/

				//Duplicated from above twice, not the best solution...
				if (document.getElementById('page-mathml-latex') != null) {
					document.getElementById('source-mathml').onfocus = function () {
						source = "mathml";
					}

					document.getElementById('source-latex').onfocus = function () {
						source = "latex";
					}

					window.changeSourceTo = function (newSource) {
						lastSource = null;
						source = newSource;
						document.getElementById('source-mathml').value = '';
						document.getElementById('source-mathml').style.color = null;
						document.getElementById('source-mathml_set').style.display = null;
					}

					if (document.getElementById('source-mathml') != null) {
						document.getElementById('source-mathml').oninput = document.getElementById('source-mathml').onkeypress = function () {
							waitingForSetMathML = true;
							document.getElementById('source-mathml').style.color = '#000';
							document.getElementById('source-mathml_set').style.display = 'block';
						}
					}

					if (document.getElementById('source-mathml_set_button') != null) {
						document.getElementById('source-mathml_set_button').onclick = function () {
							if (source == 'mathml') {
								editor.setMathML(document.getElementById('source-mathml').value);
							}
							else if (source == 'latex') {
								editor.setMathML(getMathML(document.getElementById('source-latex').value));
							}

							document.getElementById('source-mathml').style.color = null;
							document.getElementById('source-mathml_set').style.display = null;
							waitingForSetSource = false;
						}
					}

					if (document.getElementById('source-mathml_cancel_button') != null) {
						document.getElementById('source-mathml_cancel_button').onclick = function () {
							if (source == 'mathml') {
								document.getElementById('source-mathml').value = editor.getMathML();
								document.getElementById('source-mathml').style.color = null;
							}
							else if (source == 'latex') {
								getLaTeX(editor.getMathML(), function (content) {
									document.getElementById('source-mathml').value = content;
									document.getElementById('source-mathml').style.color = null;
								});
							}

							document.getElementById('source-mathml_set').style.display = null;
							waitingForSetSource = false;
						}
					}


					window.changeSourceTo = function (newSource) {
						lastSource = null;
						source = newSource;
						document.getElementById('source-latex').value = '';
						document.getElementById('source-latex').style.color = null;
						document.getElementById('source-latex_set').style.display = null;
					}

					if (document.getElementById('source-latex') != null) {
						document.getElementById('source-latex').oninput = document.getElementById('source-latex').onkeypress = function () {
							waitingForSetMathML = true;
							document.getElementById('source-latex').style.color = '#000';
							document.getElementById('source-latex_set').style.display = 'block';
						}
					}

					if (document.getElementById('source-latex_set_button') != null) {
						document.getElementById('source-latex_set_button').onclick = function () {
							if (source == 'mathml') {
								editor.setMathML(document.getElementById('source-mathml').value);
							}
							else if (source == 'latex') {
								editor.setMathML(getMathML(document.getElementById('source-latex').value));
							}

							document.getElementById('source-latex').style.color = null;
							document.getElementById('source-latex_set').style.display = null;
							waitingForSetSource = false;
						}
					}

					if (document.getElementById('source-latex_cancel_button') != null) {
						document.getElementById('source-latex_cancel_button').onclick = function () {
							if (source == 'mathml') {
								document.getElementById('source-latex').value = editor.getMathML();
								document.getElementById('source-latex').style.color = null;
							}
							else if (source == 'latex') {
								getLaTeX(editor.getMathML(), function (content) {
									document.getElementById('source-latex').value = content;
									document.getElementById('source-latex').style.color = null;
								});
							}

							document.getElementById('source-latex_set').style.display = null;
							waitingForSetSource = false;
						}
					}
				}
			}
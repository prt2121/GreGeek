/**
 * 
 */
package com.prat.gregeek.model;

/**
 * @author Prat
 * 
 */
public class Word implements Comparable<Word> {
	private String word;
	private Integer id;
	private String definition;
	private String example;
	private String pos;
//	private ArrayList<String> synonyms;
	private String synonyms;
	private Integer noteId;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word.trim().toLowerCase();
//		this.word = word;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}
	
	

	@Override
	public int compareTo(Word word) {
		String wordStr = word.getWord();
		return this.word.compareToIgnoreCase(wordStr);
	}
	
	

	@Override
	public boolean equals(Object o) {
		if(o.getClass() != this.getClass())
			return false;
		Word w = (Word) o;
		if(this.word.equalsIgnoreCase(w.getWord()))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
//		Log.v("", this.word.toLowerCase().toString());
		return this.word;
		//return super.toString();
	}

	
}

package words.environment;
import java.util.LinkedList;

import words.exceptions.WordsProgramException;
import words.exceptions.WordsRuntimeException;

/**
 * An abstract action for a WordsObject that can be enqueued on the object's action queue and later executed or expanded.
 */
public abstract class Action {
	public abstract boolean isExecutable();
	public final boolean isExpandable() { return !isExecutable(); }

	/**
	 * Execute the action if it is expandable.
	 */
	public final void execute(WordsObject object, Environment environment) throws WordsProgramException {
		assert isExecutable() : "Attempted to execute non-executable action";
		doExecute(object, environment);
	};

	/**
	 * Expand the action if it is expandable.
	 * @throws WordsRuntimeException
	 */
	public final LinkedList<Action> expand(WordsObject object, Environment environment) throws WordsProgramException {
		assert isExpandable() : "Attempted to expand non-expandable action";
		return doExpand(object, environment);
	};

	/**
	 * Actually execute the action.  Guaranteed to be called only on executable actions.
	 */
	protected abstract void doExecute(WordsObject object, Environment environment) throws WordsProgramException;

	/**
	 * Actually expand the action.  Guaranteed to be called only on expandable actions.
	 * @throws WordsRuntimeException
	 * @throws WordsProgramException
	 */
	protected abstract LinkedList<Action> doExpand(WordsObject object, Environment environment) throws WordsProgramException;
}
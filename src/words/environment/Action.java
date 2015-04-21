package words.environment;
import java.util.LinkedList;

import words.exceptions.*;

/**
 * An abstract action for a WordsObject that can be enqueued on the object's action queue and later executed or expanded.
 */
public abstract class Action {
	protected Scope scope;		// Each action has an associated scope which was active at the time that action was enqueued
	
	public Action(Scope scope) {
		this.scope = scope;
	}
	
	public abstract boolean isExecutable();
	public final boolean isExpandable() { return !isExecutable(); }

	/**
	 * Execute the action in its associated scope if it is expandable.
	 */
	public final void execute(WordsObject object, Environment environment) throws WordsProgramException {
		assert isExecutable() : "Attempted to execute non-executable action";
		
		// We must set up the scope associated with this action to facilitate the evaluation
		environment.pushExistingScope(scope);
		doExecute(object, environment);
		environment.popScope();
	};

	/**
	 * Expand the action in its associated scope if it is expandable.
	 */
	public final LinkedList<Action> expand(WordsObject object, Environment environment) throws WordsProgramException {
		assert isExpandable() : "Attempted to expand non-expandable action";
		
		// We must set up the scope associated with this action to facilitate the evaluation
		environment.pushExistingScope(scope);
		LinkedList<Action> result = doExpand(object, environment);
		environment.popScope();
		
		return result;
	};

	/**
	 * Actually execute the action.  Guaranteed to be called only on executable actions.
	 */
	protected abstract void doExecute(WordsObject object, Environment environment) throws WordsProgramException;

	/**
	 * Actually expand the action.  Guaranteed to be called only on expandable actions.
	 */
	protected abstract LinkedList<Action> doExpand(WordsObject object, Environment environment) throws WordsProgramException;
}
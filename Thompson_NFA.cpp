#include<iostream>
#include<vector>
#include<string>
#include<set>
#include<stack>

using namespace std;


struct trans {
	int vertex_from;
	int vertex_to;
	char trans_symbol;
};


class NFA {
public:
	vector<int> vertex;
	vector<trans> transitions;
	int final_state;

	NFA() {

	}

	int get_vertex_count() {
		return vertex.size();
	}

	void set_vertex(int no_vertex) {
		for(int i = 0; i < no_vertex; i++) {
			vertex.push_back(i);
		}
	}

	void set_transition(int vertex_from, int vertex_to, char trans_symbol) {
		trans new_trans;
		new_trans.vertex_from = vertex_from;
		new_trans.vertex_to = vertex_to;
		new_trans.trans_symbol = trans_symbol;
		transitions.push_back(new_trans);
	}

	void set_final_state(int fs) {
		final_state = fs;
	}

	int get_final_state() {
		return final_state;
	}

	void display() {
		trans new_trans;
		cout<<"\n";
		for(int i = 0; i < transitions.size(); i++) {
			new_trans = transitions.at(i);
			cout<<"q"<<new_trans.vertex_from<<" --> q"<<new_trans.vertex_to<<" : Symbol - "<<new_trans.trans_symbol<<endl;
		}
		cout<<"\nThe final state is q"<<get_final_state()<<endl;
	}
};



NFA concat(NFA a, NFA b) {
	NFA result;
	result.set_vertex(a.get_vertex_count() + b.get_vertex_count());
	int i;
	trans new_trans;

	for(i = 0; i < a.transitions.size(); i++) {
		new_trans = a.transitions.at(i);
		result.set_transition(new_trans.vertex_from, new_trans.vertex_to, new_trans.trans_symbol);
	}

	result.set_transition(a.get_final_state(), a.get_vertex_count(), '^');

	for(i = 0; i < b.transitions.size(); i++) {
		new_trans = b.transitions.at(i);
		result.set_transition(new_trans.vertex_from + a.get_vertex_count(), new_trans.vertex_to + a.get_vertex_count(), new_trans.trans_symbol);
	}

	result.set_final_state(a.get_vertex_count() + b.get_vertex_count() - 1);

	return result;
}


NFA kleene(NFA a) {
	NFA result;
	int i;
	trans new_trans;
	
	result.set_vertex(a.get_vertex_count() + 2);

	result.set_transition(0, 1, '^');

	for(i = 0; i < a.transitions.size(); i++) {
		new_trans = a.transitions.at(i);
		result.set_transition(new_trans.vertex_from + 1, new_trans.vertex_to + 1, new_trans.trans_symbol);
	}

	result.set_transition(a.get_vertex_count(), a.get_vertex_count() + 1, '^');
	result.set_transition(a.get_vertex_count(), 1, '^');
	result.set_transition(0, a.get_vertex_count() + 1, '^');

	result.set_final_state(a.get_vertex_count() + 1);

	return result;
}


NFA or_selection(vector<NFA> selections, int no_of_selections) {
	NFA result;
	int vertex_count = 2;
	int i, j;
	NFA med;
	trans new_trans;

	for(i = 0; i < no_of_selections; i++) {
		vertex_count += selections.at(i).get_vertex_count();
	}

	result.set_vertex(vertex_count);
	
	int adder_track = 1;

	for(i = 0; i < no_of_selections; i++) {
		result.set_transition(0, adder_track, '^');
		med = selections.at(i);
		for(j = 0; j < med.transitions.size(); j++) {
			new_trans = med.transitions.at(j);
			result.set_transition(new_trans.vertex_from + adder_track, new_trans.vertex_to + adder_track, new_trans.trans_symbol);
		}
		adder_track += med.get_vertex_count();

		result.set_transition(adder_track - 1, vertex_count - 1, '^');
	}

	result.set_final_state(vertex_count - 1);

	return result;
}
